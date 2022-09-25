import React, { Component } from 'react';
import { Text, View,Image,ImageBackground,StyleSheet,TextInput,Button,
TouchableOpacity } from 'react-native';

import{name,myfn }from"./source/js/test.js";



export default class Mybutton extends Component {

static defaultProps={
  name:'芳芳',
}


  render() {
    
      

    return (
      
        <View style={{ justifyContent: "center", alignItems: "center" }}>
          <Text style={{fontSize:36}} >
         Hello,{this.props.name}!

          </Text>
        </View>
    );
  }
}
