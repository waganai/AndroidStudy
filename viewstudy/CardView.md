# 胡标学习笔记 - CardView

[toc]

# 一、CardView简介
## developers原文地址和翻译
### 原文地址
<https://developer.android.google.cn/reference/androidx/cardview/widget/CardView?hl=en>
### 翻译
&emsp;&emsp;一个带有圆角和阴影的FrameLayout  

&emsp;&emsp;CardView在Lollipop版本上使用elevation属性处理阴影效果，在更老的版本上则使用自定义的模拟阴影。  

&emsp;&emsp;因为圆角裁剪的成本高昂，在Lollipop之前的版本上，CardView不会裁剪与圆角相交的子View。相反，它添加内边距padding以避免这种交叉(请参阅setPreventCornerOverlap(boolean)来改变这种行为)。  

&emsp;&emsp;在Lollipop版本之前，CardView会为内容添加padding并在该区域绘制阴影。这个padding在左右两边等于maxCardElevation + (1 - cos45) * cornerRadius，在顶部和底部maxCardElevation * 1.5 + (1 - cos45) * cornerRadius。  

&emsp;&emsp;因为padding是用来为偏移阴影内容的，所以你不能在CardView上设置padding。相反，你可以在XML中使用content padding属性，或者在代码中使用setContentPadding(int, int, int, int)方法来设置CardView的边缘和CardView的子节点之间的内边距padding。  

&emsp;&emsp;请注意，如果您为CardView指定精确的尺寸，由于阴影的存在，它的内容区域在Lollipop之前和之后的版本之间将有所不同。通过在不同版本上使用特定的资源值，您可以避免这些差异。另外，如果你想让CardView在Lollipop和之后的版本上添加内填充padding，你可以调用setUseCompatPadding(boolean)并传递true。  

&emsp;&emsp;为了使用向后兼容的方式更改CardView的elevation，请使用setCardElevation(float)。CardView将在Lollipop和之前的版本上使用elevation 接口，在Lollipop之前的版本上，它将改变阴影的大小。为了避免在阴影大小改变时移动视图，阴影大小被getMaxCardElevation()固定。如果你想动态改变elevation，你应该在初始化CardView时调用setMaxCardElevation(float)。  

&emsp;&emsp;  
