import React, { Component } from 'react';
import { Text, View,Image,ImageBackground,StyleSheet,TextInput,Button,
TouchableOpacity } from 'react-native';
import Mybutton from './source/js/Mybutton.js';

import{name,myfn }from"./source/js/test.js";


function fn(x, y = 10){
  return x + y;
}   
var fn1 = (x, y = 10)=> x + y



export default class App extends Component {

constructor(props){
  super(props);

  this.state={
    titleText:"标题",
    bodyText:"放呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃eeeeeee",
    text:"显示输入内容："
  };
}

  render() {
    let pic={
      uri:"https://www.baidu.com/img/bd_logo1.png"
    }
    let pic1="./source/img/2019121116261859420.jpg"
    let pic2="./source/img/bgg.jpg"
      

    return (
      
        <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
          <ImageBackground source={require(pic2)}>
          <Text style={{fontSize:26,color:"#ff8888",textAlign:'center'}} >
            { fn(5)}
            &nbsp;
            {fn1(6)}
            &nbsp;
            {console.log(myfn())}
            {myfn()}
            <Text style={[styles.bigBlue,styles.textBlue,{fontFamily:"宋体"}]}>
              自定义文字样式
            </Text>
          </Text>

          <Text style={[styles.text1]} onPress={()=>this.setState({titleText:"hello"})}>
            {this.state.titleText}{'\n'}
            </Text>
          <Text numberOfLines={2} style={[styles.text1]}>//只显示1行，超出范围省略号{'\n'}
          {this.state.bodyText}

          </Text>

          <Image source={pic} style={{width:300,height:20,flex:1}}/>
          <Image source={require(pic1)} style={{width:300,height:100,flex:1}}/>

<Text style={styles.day330} >{this.state.text}</Text>
<TextInput style={styles.day3301} 
placeholder="请输入内容"
editable={true}
multiline={true}//显示多行内容
numberOfLines={2}
//secureTextEntry={true}//密码框
            onChangeText={(text)=>this.setState({text:text})}/>

<Button 
color='#fcb656'
title='我是个按钮'
/>



<TouchableOpacity style={styles.btn1} onPress={()=>alert("你好")}>
<ImageBackground source={require(pic1)} style={styles.imgbg}
//图片要写上宽度长度
>
<Text style={styles.textBlue}>我也是个按钮</Text>
</ImageBackground>
</TouchableOpacity>

<View style={{ justifyContent:'flex-end'}}>
<Mybutton name='芳芳' />
<Mybutton name='牛牛' />
<Mybutton name='大番薯' />
</View>

<View style={{flexDirection: 'row', height:100}}>
        <View style={{flex: 1, backgroundColor: 'powderblue'}} />
        <View style={{flex: 2, backgroundColor: 'red'}} />
        <View style={{flex: 3, backgroundColor: 'steelblue'}} />
</View>
<View style={{flexDirection:'column-reverse', height:100}}>
        <View style={{flex: 1, backgroundColor: 'powderblue'}} />
        <View style={{flex: 2, backgroundColor: 'red'}} />
        <View style={{flex: 3, backgroundColor: 'steelblue'}} />
</View>

<View style={{ flexDirection: 'row',justifyContent:"space-around", alignItems:'baseline' }}>
        <View style={{width:30, backgroundColor: 'powderblue'}} />
        <View style={{width:30, backgroundColor: 'red'}} />
        <View style={{width:30, backgroundColor: 'steelblue'}} />
</View>
<View style={{flexDirection: 'row', height:100 ,justifyContent:'space-between'}}>
        <View style={{width:100, backgroundColor: 'powderblue'}} />
        <View style={{width:100, backgroundColor: 'red'}} />
        <View style={{width:100, backgroundColor: 'steelblue'}} />
</View>

            
          </ImageBackground>
        </View>
    );
  }
}
const styles = 			 StyleSheet.create({
  bigBlue: {
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 30,
  },
  red: {
    color: 'red',
  },
  textBlue: {
    backgroundColor: 'white',
    textDecorationLine: 'underline',	//文字加上下划线
    color: 'blue',
    textShadowOffset: {width: 2, height: 2},	//加上阴影
    textShadowRadius: 1,
    textShadowColor: '#00cccc'
  },
  text1:{
    fontSize:24,
    color:'#fff888'
  },
  day330:{
    fontSize:20,
    color:'blue'
  },
  day3301:{
    fontSize:28,
    borderWidth:2,
    borderColor:'#ffab33',
    margin:10
  },
  btn1:{
    width:150,
    height:50,
    justifyContent:'center',
    alignItems:'center',
    backgroundColor:'#ee3',
    borderWidth:2,
    borderColor:'#da2'
  },
  imgbg:{
    width:150,
    height:50,
    alignItems:'center',
    justifyContent:'center'
  }
});


