import React, { Component } from 'react';
import { Text, View,ImageBackground,StyleSheet,TextInput,
TouchableOpacity} from 'react-native';
import { CheckBox } from 'react-native-elements';

class reg extends Component{


    render(){
        let pic_bg="E:/Android/fang/source/img/bg.jpg"
        
        return (
          <ImageBackground source={require(pic_bg)} style={style_img.bg}>
            <View >
              
            <View style={style_view[3]}>
              <TouchableOpacity style={[style_btn[2],{flex:1}]}>
            <Text style={[style_text.title1,style_btn[2]]} >◁ </Text>
            </TouchableOpacity>
              <Text style={[style_text.title1,{flex:6,paddingRight:'5%'}]} >注册 </Text>
             </View>
    
              <View style={style_view[1]}>
    
              <View style={style_view[2]}>
              <Text style={style_text.lable1}>用户名：</Text>
              <TextInput style={style_text.input1}></TextInput>
              </View>
    
              <View style={style_view[2]}>
              <Text style={style_text.lable1}>密码：</Text>
              <TextInput style={style_text.input1}></TextInput>
              </View>

              <View style={style_view[2]}>
              <Text style={style_text.lable1}>确认密码：</Text>
              <TextInput style={style_text.input1}></TextInput>
              </View>

              <View style={style_view[2]}>
              <Text style={style_text.lable1}>性别：</Text>
              <TextInput style={style_text.input1}></TextInput>
             
              </View>

              <View style={style_view[2]}>
              <Text style={style_text.lable1}>手机号：</Text>
              <TextInput style={style_text.input1}></TextInput>
              </View>

              <View style={style_view[2]}>
              <Text style={style_text.lable1}>邮箱：</Text>
              <TextInput style={style_text.input1}></TextInput>
              </View>
    
              
              <TouchableOpacity style={style_btn[1]} >
                <Text style={style_text.lable1}>注册</Text>
              </TouchableOpacity>
              
    
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
      flex:1.5,
      fontWeight:'bold',
      color: '#666699',
    },
    input1:{
      fontSize:20,
      borderWidth:1,
      flex:2,
      borderColor:'#666699',
      paddingLeft:'10%',
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
      paddingBottom:'25%',
    },
    2: {
      flexDirection:'row',
      alignItems:'center',
      margin:15,
    },
    3:{
      flexDirection:'row',
      width:'100%',
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
      left:'29%',
      margin:20,
      paddingTop:'2%',
    },
    2: {
      alignItems:'center',
      borderColor:'#666699',
      borderWidth:1,
    },
  });

  
module.exports=reg;