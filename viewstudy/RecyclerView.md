# 胡标学习笔记 - RecyclerView

[toc]

# 一、RecyclerView简介
## developers原文地址和翻译
### 原文地址
<https://developer.android.google.cn/reference/androidx/recyclerview/widget/RecyclerView?hl=en>
### 翻译
&emsp;&emsp;为大数据集提供有限窗口的灵活的View  
# 二、RecyclerView主要部件
### ItemDecoration
#### developer原文地址链接
<https://developer.android.google.cn/reference/androidx/recyclerview/widget/RecyclerView.ItemDecoration?hl=en>
#### 简介翻译
&emsp;&emsp;ItemDecoration允许应用程序从适配器的数据集中向特定的项目视图添加特殊的绘图和布局偏移量。这对于绘制项目之间的分割线、highlights高亮、可视化分组边界等非常有用。  

&emsp;&emsp;所有的ItemDecoration都是按照它们添加的顺序绘制的，在item views之前绘制(使用onDraw()方法)，在item views之后绘制(使用onDrawOver(Canvas, RecyclerView, RecyclerView. state)方法)。  

#### 方法介绍
##### getItemOffsets
```java
public void getItemOffsets (Rect outRect, 
                View view, 
                RecyclerView parent, 
                RecyclerView.State state)
```
&emsp;&emsp;纠正给定item的任何方向的偏移量。outRect的每个字段都用来指定item view应该插入的像素值，类似于padding或margin。默认实现将outRect的边界全部设置为0并返回。

&emsp;&emsp;如果这个ItemDecoration不影响项目视图的定位，它应该在返回之前将outRect的所有四个字段(left、top、right、bottom)设置为零。

&emsp;&emsp;如果你需要访问适配器Adapter以获取额外的数据，你可以调用RecyclerView.getChildAdapterPosition(View)来获得item view在适配器Adapter中的位置position。  
|Parameters |  |
|------    |---------    |
|outRect  |  接收输出的矩形区域    |
|view      |  需要被装饰的子View      |
|parent      |  这个ItemDecoration锁装饰的RecyclerView      |
|state      |  这个RecyclerView的当前状态 |      
##### ~~getItemOffsets~~
```java
public void getItemOffsets (Rect outRect, 
                int itemPosition, 
                RecyclerView parent)
```
##### onDraw
```java
public void onDraw (Canvas c, 
                RecyclerView parent, 
                RecyclerView.State state)
```
&emsp;&emsp;在提供给RecyclerView的画布上绘制任何适当的装饰。使用此方法绘制的任何内容都将在项目视图绘制之前绘制，因此将显示在视图下面。
|Parameters||
|--|--|
|c|需要被绘制的画布|
|parent|ItemDecoration用来装饰的RecyclerView|
|state|这个RecyclerView的当前状态|
##### ~~onDraw~~
```java
public void onDraw (Canvas c, 
                RecyclerView parent)
```
##### onDrawOver
````java
public void onDrawOver (Canvas c, 
                RecyclerView parent, 
                RecyclerView.State state)
````
&emsp;&emsp;在提供给RecyclerView的画布上绘制任何适当的装饰。使用此方法绘制的任何内容都将在项目视图绘制之后绘制，因此将显示在视图之上。
|Parameters||
|--|--|
|c|需要被绘制的画布|
|parent|ItemDecoration用来装饰的RecyclerVie|
|state|这个RecyclerView的当前状态|
##### ~~nDrawOver~~
````java
public void onDrawOver (Canvas c, 
                RecyclerView parent)
````