import React, { Component } from 'react';
import { Text, View,ImageBackground,StyleSheet,TextInput,
TouchableOpacity} from 'react-native';


export default class App extends Component {

  render() {
    let pic_bg="./source/img/bg.jpg"

    return (
      <ImageBackground source={require(pic_bg)} style={style_img.bg}>
        <View >
          
          <Text style={style_text.title1} >登录 </Text>

          <View style={style_view[1]}>

          <View style={style_view[2]}>
          <Text style={style_text.lable1}>账号：</Text>
          <TextInput style={style_text.input1}></TextInput>
          </View>

          <View style={style_view[2]}>
          <Text style={style_text.lable1}>密码：</Text>
          <TextInput style={style_text.input1}></TextInput>
          </View>

          <View style={style_view[2]}>
          <TouchableOpacity style={style_btn[1]} >
            <Text style={style_text.lable1} >登录</Text>
            
          </TouchableOpacity>
          <TouchableOpacity style={style_btn[1]} >
            <Text style={style_text.lable1}>注册</Text>
          </TouchableOpacity>
          </View>

          </View>

        </View>
      </ImageBackground>
    );
  }
}

const style_text = 			 StyleSheet.create({
  title1: {
    color: '#666699',
    fontWeight: 'bold',
    fontSize: 40,
    backgroundColor:'#ffff',
    borderWidth:3,
    borderColor:'#99CCFF',
    textAlign:'center',
  },
  lable1:{
    fontSize:25,
    flex:1,
    fontWeight:'bold',
    color: '#666699',
  },
  input1:{
    fontSize:20,
    borderWidth:1,
    flex:2,
    borderColor:'#666699',
    paddingRight:'15%',
    marginRight:'5%',
  }
  
});
const style_img = 			 StyleSheet.create({
  bg: {
    width:'100%',
    height:'100%',
  },
});
const style_view = 			 StyleSheet.create({
  1:{
    width:'100%',
    height:'100%',
    flexDirection:'column',
    justifyContent:'center',
    paddingBottom:'70%',
  },
  2: {
    flexDirection:'row',
    alignItems:'center',
    margin:20,
  },
});
const style_btn = 			 StyleSheet.create({
  1: {
    alignItems:'center',
    width:150,
    height:60,
    backgroundColor:'#99CCFF',
    borderWidth:3,
    borderColor:'#666699',
    left:'20%',
    top:'10%',
    margin:10,
    paddingTop:'2%',
  },
});



module.exports=App;