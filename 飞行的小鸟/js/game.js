//得到小鸟盒子
let bird=document.querySelector('#bird');
//定义小鸟的top值
let birdTop=100;
//定义小鸟top的自增变化量
let deltaTop=0;
//定义小鸟的旋转角度
let birdRotate=0;
//定义小鸟的图片位移
let birdShape=0;
//得到陆地盒子
let land=document.querySelector('#land');
//管子数组，这里存放所有管子的实例
let pipeArr=[];
//得到游戏盒子
let gamebox=document.querySelector('#gamebox');
//帧
let f=0;
//得到覆盖盒子
let cover=document.querySelector('#cover');
//得到工具盒子
let tool=document.querySelector('#tool');
//得到重玩按钮
let restart=document.querySelector('#restart');

// 管子类
class Pipe {
    // 构造函数
    constructor() {
    // 创建div盒子，容纳两个管子图片
    this.pipe1 = document.createElement('div');
    this.pipe2 = document.createElement('div');
    // 添加类名
    this.pipe1.className = 'pipe1';
    this.pipe2.className = 'pipe2';
    // 添加到盒子中
    gamebox.appendChild(this.pipe1);
    gamebox.appendChild(this.pipe2);
    // 管子之间的距离
    this.space = 180;
    // 随机一个管子2（上面管子，开口朝下）的高度
    this.pipe2Height = parseInt(Math.random() * 200) + 100;
    // 根据随机出来的管子2的高度，计算管子1的高度。因为总高度是560，所以下面管子的高度就能算出来
    this.pipe1Height = 560 - this.space - this.pipe2Height;
    // 管子的left值，在最右边，所以是480
    this.left = 480;
    // 设置两个管子的高度
    this.pipe1.style['height'] = this.pipe1Height + 'px';
    this.pipe2.style['height'] = this.pipe2Height + 'px';
    // 设置管子1的top值
    this.pipe1.style['top'] = this.pipe2Height + this.space + 'px';
    // 设置两个管子的left值
    this.pipe1.style['left'] = this.left + 'px';
    this.pipe2.style['left'] = this.left + 'px';
    // 向管子数组中推入自己
    pipeArr.push(this);
    }
    // 移动方法
move () {
    if(this.left>-50){
        // 让left值减少，数值就是每帧移动的变化量
        this.left -= 5;
    }else{
        this.pipe1.style['opacity']=0;
        this.pipe2.style['opacity']=0;
    }
    
    // 设置两个管子的left值
    this.pipe1.style['left'] = this.left + 'px';
    this.pipe2.style['left'] = this.left + 'px';
    }
    
    }
    // 实例化管子
    new Pipe();
 
   let birdA1 = birdTop + 6;
// 小鸟的AABB盒之a2值
let birdA2 = birdTop + 28;
// 小鸟的AABB盒之b1值
let birdB1 = 110 + 6;
// 小鸟的AABB盒之b1值
let birdB2 = 110 + 45;


//用定时器，不断地改变小鸟的形态
let timer1=setInterval(() => {

    if(birdShape>-114){birdShape-=1;}
    else{birdShape=0;}
    land.style.backgroundPosition=birdShape+'px 0';

    if(birdShape>-112){birdShape-=56;}
    else{birdShape=0;}
    bird.style.backgroundPosition=birdShape+'px 0';
    // 小鸟的AABB盒之a1值
birdA1 = birdTop + 6;
// 小鸟的AABB盒之a2值
birdA2 = birdTop + 28;
// 小鸟的AABB盒之b1值
birdB1 = 110 + 6;
// 小鸟的AABB盒之b1值
birdB2 = 110 + 45;
// 在控制台输出小鸟的AABB盒数据
console.log(birdA1, birdA2, birdB1, birdB2);

}, 100);

//用定时器，不断地改变小鸟的top属性值
let timer2=setInterval(() => {
    //让变化量变大
    deltaTop+=0.3;
    //小鸟的top自增，在自增一个变化量
    birdTop+=deltaTop;
    //验收
    if(birdTop>520){
        birdTop=520;
    }
    else if(birdTop<0){
        birdTop=0;
    }
    //设置小鸟top值，改变小鸟位置
    bird.style.top= birdTop+'px';
    //让小鸟旋转
    birdRotate+=2;
    //设置小鸟的旋转值，改变小鸟位置
    bird.style.transform='rotate('+birdRotate+'deg)';

    f++;
    if(f%100==0){
        new Pipe();
    }

    // 更新所有管子。遍历pipes数组中的每一个管子
    pipeArr.forEach(item => {
    // 让遍历出的这个管子（当然每一个管子都会被遍历到，所以这里指的是所有管子）移动。
    item.move();
    // 判断有没有撞鸟
if (
    // 情况1：撞到上管子了
    item.left < birdB2 && item.pipe2Height > birdA1 && item.left + 88 >
    birdB1
    ||
    // 情况2：撞到下管子了
    item.left < birdB2 && item.pipe2Height + item.space < birdA2 &&
    item.left + 88 > birdB1
    ||
    //情况3：摔到地上
    (birdA1==526||birdA2==548)
    ) {
        
    // 清除定时器
    clearInterval(timer1);
    clearInterval(timer2);
    //出现阴影
    cover.style.opacity=1;
    //让工具盒子出现
    tool.style.display='block';

    // 让大地停止运动
    //land.className = 'stop';
    }
    
    });

}, 20);//20毫秒=0.02秒


//点击游戏盒子
gamebox.onclick=()=>{
    deltaTop=-8;
    birdRotate=-60;
}
 