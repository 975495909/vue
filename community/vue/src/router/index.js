import {
    createRouter,
    createWebHistory
} from 'vue-router'
import Layout from '../layout/Layout'
import Content from '../layout/Content'
import Index from '../views/content/Index.vue'
// 业主管理
import OwnerView from '../views/content/OwnerView.vue'
import CarView from '../views/content/CarView.vue'
import PetView from '../views/content/PetView.vue'
// 楼栋管理
import BuildingView from '../views/content/BuildingView.vue'
import HouseView from '../views/content/HouseView.vue'
import LeaseView from '../views/content/LeaseView.vue'
import PropertyView from '../views/content/PropertyView.vue'
// 服务管理
import ActivityView from '../views/content/ActivityView.vue'
import RepairView from '../views/content/RepairView.vue'
import ComplaintView from '../views/content/ComplaintView.vue'
// 收费管理
import ChargeView from '../views/content/ChargeView.vue'
import ChargeTypeView from '../views/content/ChargeTypeView.vue'
// 人力管理
import DepartmentTypeView from '../views/content/DepartmentTypeView.vue'
import ManpowerView from '../views/content/ManpowerView.vue'
// 资产管理
import ResourcesView from '../views/content/ResourcesView.vue'
// 权限管理
import ManagerView from '../views/content/ManagerView.vue'

import Personal from '../views/Personal.vue'
import Login from '../views/Login'

const routes = [{
        path: '/',
        name: 'login',
        component: Login
    },
    {
        path: '/index',
        name: 'layout',
        component: Layout,
        //重定向
        redirect: '/index',
        children: [{
            path: '',
            name: 'content',
            component: Content,
            //重定向
            redirect: '/index',
            children: [{
                    path: '/personal',
                    name: '个人中心',
                    component: Personal
                },{
                    path: '/index',
                    name: '首页',
                    component: Index
                },{
                    path: '/activity',
                    name: '活动信息',
                    component: ActivityView
                },{
                    path: '/repair',
                    name: '报修信息',
                    component: RepairView
                },{
                    path: '/complaint',
                    name: '投诉信息',
                    component: ComplaintView
                },
                {
                    path: '/owner',
                    name: '业主信息',
                    component: OwnerView
                },
                {
                    path: '/car',
                    name: '车辆信息',
                    component: CarView
                },
                {
                    path: '/pet',
                    name: '宠物信息',
                    component: PetView
                },
                {
                    path: '/building',
                    name: '楼栋信息',
                    component: BuildingView
                }, 
                {
                    path: '/house',
                    name: '房屋信息',
                    component: HouseView
                },
                {
                    path: '/lease',
                    name: '租赁信息',
                    component: LeaseView
                },
                {
                    path: '/property',
                    name: '房产信息',
                    component: PropertyView
                },
                {
                    path: '/chargetype',
                    name: '收费类型',
                    component: ChargeTypeView
                }, 
                
                {
                    path: '/charge',
                    name: '项目信息',
                    component: ChargeView
                }, 
                
                {
                    path: '/departmenttype',
                    name: '部门类型',
                    component: DepartmentTypeView
                }, 
                
                {
                    path: '/manpower',
                    name: '职员信息',
                    component: ManpowerView
                },
                 
                {
                    path: '/resources',
                    name: '资产信息',
                    component: ResourcesView
                }, 
                
                {
                    path: '/manager',
                    name: '管理员信息',
                    component: ManagerView
                }, 
            ]
        }]
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
