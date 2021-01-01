# 胡标学习笔记 - View

[toc]

## 一、View简介
### developers原文地址和翻译
#### 原文地址
<https://developer.android.google.cn/reference/android/view/View?hl=en>
#### 翻译
&emsp;&emsp;这个类表示用户界面组件的基本构建块。View在屏幕上占据一个矩形区域，并且负责绘图和事件处理。View是widgets的基类，用于创建交互式UI组件(Button、TextView等)。ViewGroup子类则是layouts的基类，layouts是保存其他视图(或其他ViewGroup)并定义其布局属性的不可见容器。  
## 二、View的使用
&emsp;&emsp;window中的所有view都排列在一个tree结构中。可以从代码中添加View，也可以在一个或多个XML布局文件中指定view tree来添加view。view有许多专门化的子类，它们充当控件或能够显示文本、图像或其他内容。  

&emsp;&emsp;一旦你创建了一个视图树，通常有一些常见的操作你可能希望执行:
- 设置属性:例如设置一个TextView的文本。可用的属性和设置它们的方法在view的不同子类中会有所不同。请注意，构建时已知的属性可以在XML布局文件中设置。  
- 设置焦点:framework将处理移动焦点以响应用户输入。要将焦点强制放在特定的view上，可以调用requestFocus()。
- 设置监听器:view允许客户端设置监听器，当view发生有趣的事情时，监听器将被通知。例如，所有view都允许您设置一个监听器，以便在视图获得焦点或失去焦点时通知它。你可以使用setOnFocusChangeListener注册这样一个监听器。其他view子类提供了更专门的监听器。例如，当按钮被单击时，按钮将向客户端公开一个监听器。
- 设置可见性:你可以使用setVisibility(int)隐藏或显示视图。  
  
&emsp;&emsp;注意:Android框架负责测量、布局和绘制视图。你不应该调用在视图上执行这些操作的方法，除非你真的在实现一个ViewGroup。

## 三、实现自定义View
&emsp;&emsp;要实现自定义视图，通常首先要覆盖框架在所有视图上调用的一些标准方法。您不需要覆盖所有这些方法。事实上，你可以从重写onDraw(android.graphics.Canvas)开始。
		  
|Category|Methods|Description|
|--|--|--|
|Creation|	Constructors	|当从代码创建View时，会调用构造函数的一种形式，当从布局文件中创建View时，会调用构造函数的一种形式。第二种形式应该解析和应用在布局文件中定义的所有属性。|
||onFinishInflate()	|在View和他的所有子View从xml布局文件中创建之后调用。|
|Layout	|onMeasure(int, int)	|用来确定这个View和它的所有子view的大小
||onLayout(boolean, int, int, int, int)|当View给它的所有子View指定尺寸和位置时调用|
||onSizeChanged(int, int, int, int)|当View的尺寸发生变化时调用|
|Drawing|onDraw(android.graphics.Canvas)|当这个View绘制自己的内容时调用|
|Event processing|onKeyDown(int, android.view.KeyEvent)|当一个硬件按键时间发生时调用|
||onKeyUp(int, android.view.KeyEvent)|当一个硬件按键UP事件发生时调用|
||onTrackballEvent(android.view.MotionEvent)|当一个为轨迹球移动事件发生时调用|
||onTouchEvent(android.view.MotionEvent)|当屏幕触摸事件发生时调用|
|Focus|onFocusChanged(boolean, int, android.graphics.Rect)|当View获得或者失去焦点时调用|
||onWindowFocusChanged(boolean)|当该View所在的Window获得或者失去焦点时调用|
|Attaching|onAttachedToWindow()|当View被附着在一个Window上时调用|
||onDetachedFromWindow()|当View从它所在的Window上剥离时调用|
||onWindowVisibilityChanged(int)|当View所在的Window的可见性发生变化时调用|

## 三、IDs
&emsp;&emsp;View可以有一个与它们相关联的整数id。这些id通常在布局XML文件中分配，用于在视图树中查找特定的视图。一个常见的模式是:
- 在布局文件中定义一个Button，并且将它和一个唯一的ID相关联；
```xml
<Button
     android:id="@+id/my_button"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:text="@string/my_button_text"/>
```
- 在Activity的onCreate()方法中查找这个Button:ß
```java
Button myButton = findViewById(R.id.my_button);
```
&emsp;&emsp;View的id在整个tree结构中是不是唯一的，但最好确保它们至少在您所搜索的tree的部分中是唯一的。

## 四、Position
&emsp;&emsp;View的几何形状是矩形。视图有一个位置(表示为一对左坐标和顶坐标)和两个维度(表示为宽度和高度)。位置和尺寸的单位是像素。  

&emsp;&emsp;可以通过调用getLeft()和getTop()方法来获取View的位置。前者返回表示View的矩形区域的左坐标或X坐标。后者返回表示View的矩形区域的顶部坐标，即Y坐标。这两个方法都返回View相对于其父View的位置。例如，当getLeft()返回20时，这意味着视图位于其直接父视图的左边缘右侧20个px。  

&emsp;&emsp;此外，还提供了一些方便的方法来避免不必要的计算，即getRight()和getBottom()。这些方法返回表示视图的矩形的右边缘和下边缘的坐标。例如，调用getRight()类似于下面的计算:getLeft() + getWidth()(有关宽度的更多信息，请参阅Size)。  
## 五、Size, padding and margins
&emsp;&emsp;View的大小用宽度和高度表示。一个View实际上有两对宽度和高度值。  

&emsp;&emsp;第一对被称为measured width和measred height。这些维度定义了一个View想要在它的父View中有多大。他们可以通过调用getMeasuredWidth()和getMeasuredHeight()方法来获得。  

&emsp;&emsp;第二组简单地称为width和height，有时也称为drawing width和drawing height。这些尺寸定义了在绘制时和布局后屏幕上视图的实际尺寸。这些值可能与measured width和measured height不同，但不一定。width和height可以通过调用getWidth()和getHeight()获得。  

&emsp;&emsp;为了测量它的尺寸，View要考虑它的padding。View的左、上、右和下部分的padding用像素px表示。paddiing可用于将Viewe的内容偏移特定数量的像素。例如，左内边距为2将把视图的内容推到左边边缘的右边2个像素。padding可以使用setPadding(int, int, int, int)或setPaddingRelative(int, int, int, int)方法设置，并通过调用getPaddingLeft()， getPaddingTop()， getpaddinggright ()， getPaddingBottom()， getPaddingStart()， getPaddingEnd()来查询。  

&emsp;&emsp;尽管View可以定义padding，但它不提供任何对margin的支持。然而，ViewGroup提供了这样的支持。参考ViewGroup和ViewGroup。进一步的信息是MarginLayoutParams。  

## 六、Layout
&emsp;&emsp;Layout是一个分两步的过程:一个measure过程和一个layout过程。measure过程在measure(int, int)方法中实现，是View tree 结构的自顶向下遍历过程。在递归过程中，每个View都将尺寸规范在tree结构中向下传递。在测量结束时，每个View都存储了它的测量值。第二次传递发生在layout(int, int, int, int)方法中，也是自顶向下的。在此过程中，每个父View负责使用measure过程中计算的大小定位所有子View。  

&emsp;&emsp;当一个View的measure()方法返回时，它的getMeasuredWidth()和getMeasuredHeight()值必须被设置，以及所有该View的子View的getMeasuredWidth()和getMeasuredHeight()值。一个View的measured width和measured height的值必须遵守View的父View所施加的约束。这保证了在measure过程结束之后，所有的父View都能接受子View的测量结果。父视图可以在其子视图上多次调用measure()。例如，父节点可以用unspecified的维度测量每一个子View，以确定它们想要多大，然后如果所有子View的没有被约束的尺寸的总和太大或太小，则可以用确切的数值再次调用measure()。  

&emsp;&emsp;measure过程使用两个类来传递维度信息。View使用MeasureSpec类来告诉它们的父View它们希望如何被测量和被确定位置。基本的LayoutParams类只描述View想要多大的宽度和高度。对于每个维度，它可以指定其中一个:  
-  一个确切的数值
-  MATCH_PARENT, 表示View想和它的父View一样大(减去内边距padding)
-  WRAP_CONTENT, 表示View只想足够大刚刚好包裹住它自己的内容(加上内边距padding).  
  
&emsp;&emsp;对于不同的ViewGroup子类，有不同的LayoutParams子类。例如，AbsoluteLayout有自己的LayoutParams子类，它添加了一个X和Y值。  

&emsp;&emsp;MeasureSpecs用于将需求从父View推到子View。MeasureSpec可以有以下三种模式:
- UNSPECIFIED:父View使用此参数来确定子View的所需尺寸。例如，LinearLayout可以在其子视图上调用measure()，将其高度设置为未指定，宽度设置为240，以确定子视图希望被赋予240像素的宽度有多高。
- EXACTLY: 这是由父View用来给子View施加精确的大小。子View必须使用这个大小，并保证它的所有子View都适合这个大小。
- AT_MOST: 这是父View用来给子View施加最大尺寸的。子View必须保证自己和自己所有子View都能适应这个尺寸。  

&emsp;&emsp;要初始化一个布局，调用requestLayout()。当视图认为它不能再适应当前边界时，通常会调用该方法。  

## 七、Drawing
&emsp;&emsp;绘图是通过遍历tree结构并记录任何需要更新的视图的绘图命令来处理的。在此之后，整个View tree结构的绘图命令被发布到屏幕上，剪切到新损坏的区域。  

&emsp;&emsp;View tree很大程度上是按照顺序记录和绘制的，父View画在他们的孩子之前(也就是下层？？？)，同一级的View按照他们出现View tree中顺序绘制。如果你为一个View设置了一个background drawable，那么View会在调用它的onDraw()方法之前绘制它。子View的绘制顺序可以在ViewGroup中使用ViewGroup#setChildrenDrawingOrderEnabled(boolean)来覆盖，在View中使用setZ(float)(自定义 Z 值}来覆盖。  

&emsp;&emsp;为了强制绘制View，可以调用invalidate()。