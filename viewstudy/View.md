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
## 八、Event Handling and Threading
&emsp;&emsp;View的基本周期如下：  
1. 一个事件到来并被分发到合适的View，View处理事件并通知给监听器
2. 如果在处理事件的过程中，View的边界可能需要更改，View将调用requestLayout()
3. 类似地，如果在处理事件的过程中View的外观可能需要更改，View将调用invalidate()。
4. 如果requestLayout()或invalidate()被调用，Android framework将负责measure、layout和draw适当的树。  

&emsp;&emsp;注意:整个View tree是单线程的。在任何View上调用任何方法时，你必须始终处于UI线程中。如果您正在其他线程上执行工作，并希望从该线程更新View的状态，则应该使用处Handler。

## 九、Focus Handling
&emsp;&emsp;android framework在响应用户输入的过程中处理常规焦点移动。这包括在View的被删除或隐藏，或者新的View可用时的焦点的改变。View通过isFocusable()方法表示它们是否愿意获取焦点。要更改View是否可以获取焦点，调用setFocusable(boolean)。当在触摸模式下(见下面的注释)，视图通过isFocusableInTouchMode()来表示他们是否仍然想要焦点，并可以通过setFocusableInTouchMode(boolean)来更改。  

&emsp;&emsp;焦点移动是基于在给定方向上找到最近的邻居的算法。在极少数情况下，默认算法可能与开发人员的预期行为不匹配。在这些情况下，你可以通过在布局文件中使用这些XML属性来提供显式的覆盖:
```xml
 nextFocusDown
 nextFocusLeft
 nextFocusRight
 nextFocusUp
```  

&emsp;&emsp;要让特定视图获得焦点，请调用requestFocus()。  
 
## 十、Touch Mode
&emsp;&emsp;当用户通过方向键(如方向键)导航用户界面时，有必要将焦点放在Button等可操作的项目上，这样用户就可以看到需要输入的内容。但是，如果设备具有触摸功能，并且用户通过触摸它开始与界面交互，就不再需要总是突出显示或聚焦特定的View。这导致了一种名为“触摸模式”的交互模式的产生。

&emsp;&emsp;对于能够触摸的设备，一旦用户触摸屏幕，设备将进入触摸模式。从现在开始，只有isFocusableInTouchMode()为true的视图才可以聚焦，比如文本编辑小部件。其他可触摸的视图，比如按钮，在被触摸时不会聚焦;它们只会触发点击监听器。  

&emsp;&emsp;任何时候用户点击方向键，如D-pad方向键，设备将退出触摸模式，并找到一个View获取焦点，这样用户就可以在不再次触摸屏幕的情况下恢复与用户界面的交互。  

&emsp;&emsp;触摸模式的状态是跨活动维护的。调用isInTouchMode()查看设备当前是否处于触摸模式。  

## 十一、Scrolling
&emsp;&emsp;android framework为希望内部滚动内容的View提供了基本支持。这包括跟踪X和Y滚动偏移量以及绘制滚动条的机制。参阅scrollBy(int, int)， scrollTo(int, int)和awakenScrollBars()了解更多信息。  

## 十二、Tags
&emsp;&emsp;与id不同，tag不用于标识视图。tag本质上是可以与View相关联的额外信息。它们最常用来方便地在View中存储与View相关的数据，而不是将它们放在单独的结构中。  

```xml
<View ...
    android:tag="@string/mytag_value" />
<View ...>
    <tag android:id="@+id/mytag"
        android:value="@string/mytag_value" />
</View>
```  

&emsp;&emsp;tag也可以使用代码中的setTag(java.lang.Object)或setTag(int, java.lang.Object)指定任意对象。  

## 十三、Themes
&emsp;&emsp;默认情况下，View是使用提供给它们的构造函数的Context对象的主题创建的;然而，不同的主题可以通过在布局XML中使用android:theme属性或通过从代码传递ContextThemeWrapper到构造函数来指定。  

&emsp;&emsp;当android:theme属性在XML中使用时，指定的主题应用于inflation context的Theme(参见LayoutInflater)，并用于View本身以及任何子元素。  

&emsp;&emsp;在下面的例子中，两个View都将使用Material dark color scheme创建;然而，因为只定义了属性的子集的覆盖主题被使用了，android:colorAccent的值定义在inflatio context的theme(例如Activity theme)将被保留。  

```xml
<LinearLayout
    ...
    android:theme="@android:theme/ThemeOverlay.Material.Dark">
    <View ...>
</LinearLayout> 
```  

## 十四、Properties
&emsp;&emsp;View类公开了一个ALPHA属性，以及几个与平移相关的属性，如TRANSLATION_X和TRANSLATION_Y。这些属性既可以在属性形式中使用，也可以在命名类似的setter/getter方法中使用(例如ALPHA的setAlpha(float))。这些属性可用于在View中设置与这些呈现相关属性相关联的持久状态。属性和方法也可以与基于动画的动画一起使用，在动画部分有更多的描述。  

## 十五、Animation
&emsp;&emsp;从Android 3.0开始，让视图动画化的首选方式是使用android.animation package APIs。这些基于Animator的类会改变View对象的实际属性，比如alpha和translationX。这种行为与3.0之前的基于Animation的类形成了对比，后者只对View在显示上绘制的方式进行动画处理。特别是，ViewPropertyAnimator类使这些视图属性的动画化变得非常简单和高效。  

&emsp;&emsp;另外，你也可以使用3.0之前的animation类来为View的渲染方式制作动画。你可以使用setAnimation(android.view.animation.Animation)或startAnimation(android.view.animation.Animation)将一个动画对象附加到一个View。动画可以随时间改变视图的缩放、旋转、平移和alpha值。如果动画被附加到一个有子节点的View上，动画将影响以该节点为根的整个子View tree结构。当一个动画开始时，android framework将负责重新绘制适当的View，直到动画完成。  

## 十六、Security
&emsp;&emsp;有时，应用程序必须能够验证在用户完全知情和同意的情况下执行的操作，例如授予权限请求、进行购买或点击广告。不幸的是，恶意的应用程序可能试图通过隐藏视图的预期目的，在不知情的情况下欺骗用户执行这些操作。作为补救措施，该框架提供了一个触摸过滤机制，可以用来提高视图的安全性，这些View提供了对敏感功能的访问。  

&emsp;&emsp;要启用触摸过滤，调用setfiltertoucheswhenwards (boolean)或设置android: filtertoucheswhenwards布局属性为true。当启用时，当View所在的Window被另一个可见Window遮挡时，android framework将丢弃接收到的触摸。因此，当toast、Dialog或其他Window出现在View窗口上方时，View将不会接收触摸。  

&emsp;&emsp;对于更细粒度的安全控制，考虑覆盖onFilterTouchEventForSecurity(android.view.MotionEvent)方法来实现你自己的安全策略。参见MotionEvent # FLAG_WINDOW_IS_OBSCURED。
