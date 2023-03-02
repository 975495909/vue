import {
    createApp
} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Cookies from 'js-cookie'
import  echarts from 'echarts'

//导入element-plus相关
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


//导入css文件
import '@/assets/css/global.css'


createApp(App)
    .use(store)
    .use(router)
    .use(Cookies)
    .use(echarts)
    .use(ElementPlus, {
        locale: zhCn,
    })
    .mount('#app')


