#《基于Android的2048小游戏开发》
##41810087 黄瑞
项目创建时间：10月21日
项目介绍 :
利用Android Studio开发一个简易版的2048小游戏，实现功能包括：游戏开始界面，游戏主界面，游戏结束、重新开始游戏，游戏计分等。
当前完成功能：
新建一个游戏首页FirstActivity，点击开始游戏按钮跳入游戏主界面，点击退出游戏退出首页。添加菜单栏，Item包括设置和帮助；点击设置跳入设置页面；点击帮助出现消息对话框提示。
在MainActivity中加入重新开始和退出游戏两个按钮，使用消息对话框监听按钮的动作。添加菜单栏，菜单栏事件为提示。
游戏主界面GameActivity中实现了触控交互功能，利用监听方法监听触摸动作，利用坐标的变化值来判断手指的上下左右滑动，获取用户当前位置与移动偏移量来判断移动方向和结束位置。
实现了四个方向的移动，采用了遍历的方法对卡片的值进行赋值、计算等，实现2048游戏的逻辑运作。
为每个不同数字的卡片配置不同的颜色，使滑动效果更明显；同时增添了放缩大小的动画效果。
在MainActivity中实现了计分功能，只要卡片合并就计算分数。通过Shareprefrences存储最高分，将最高分和当前分数比较，获取最高分，实现历史最高分的存储。
还实现了卡片类的方法，添加16张卡片对象到游戏主界面GameActivity中，利用二维数组更清晰地展示了每个卡片的位置，在CardActivity中实现卡片布局以及设置获取值的功能。
在GameActivity中判断是否有合并，在每个合并动作发生时，都再添加一个随机值。
在游戏中添加随机数，运用到emptyPoints处理空点，数学的random方法设置随机概率，并通过概率大小来确定随机数的值。
实现结束游戏操作，当表格中没有空格存在或者没有合并项存在游戏就结束了，endGame方法实现了显示结束游戏对话框，添加两个监听用户动作的按钮，可以进行重新开始游戏或退出游戏。
利用onKeyDown实现了Android返回键再按一次返回系统桌面的功能。


