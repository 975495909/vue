<template>
    <div class="aside">
        <!-- 
        <el-menu class="el-menu-vertical-demo" active-text-color="#7f769c" router :default-active="currentPath">
            <el-menu-item-group>
                <el-menu-item index="index">首页</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1">
                <template #title>
                    <span>业主管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="owner">人员信息</el-menu-item>
                    <el-menu-item index="car">车辆信息</el-menu-item>
                    <el-menu-item index="pet">宠物信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                    <span>楼栋管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="building">楼栋信息</el-menu-item>
                    <el-menu-item index="house">房屋信息</el-menu-item>
                    <el-menu-item index="lease">租赁信息</el-menu-item>
                    <el-menu-item index="property">房产信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="3">
                <template #title>
                    <span>服务管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="activity">活动信息</el-menu-item>
                    <el-menu-item index="repair">报修信息</el-menu-item>
                    <el-menu-item index="complaint">投诉信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="4">
                <template #title>
                    <span>收费管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="chargetype">收费类型</el-menu-item>
                    <el-menu-item index="charge">项目信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="5">
                <template #title>
                    <span>人力管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="departmenttype">部门类型</el-menu-item>
                    <el-menu-item index="manpower">职工信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="6">
                <template #title>
                    <span>资产管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="resources">资产信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="7">
                <template #title>
                    <span>权限管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="manager">管理员信息</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>

        </el-menu> -->
        <el-menu class="el-menu-vertical-demo" active-text-color="#7f769c" router :default-active="currentPath">
            <el-menu-item-group>
                <el-menu-item index="index" @click="$emit('clickIndex')">首页</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu v-for="item in menuItems" :index="item.index" >

                <template #title><span>{{item.name}}</span></template>
                <el-menu-item-group v-for="childrenItem in item.children" @click="$emit('toTab')" >
                    <el-menu-item :index="childrenItem.index" @click="totab(childrenItem.index,childrenItem.name)">
                        {{childrenItem.name}}
                    </el-menu-item>
                </el-menu-item-group>

            </el-sub-menu>
        </el-menu>

    </div>
</template>

<script>
    export default {

        name: "Aside",
        data() {
            return {
                currentPath: this.$route.path.slice(1),
                currentName:'',
                currentIndex:'',
                menuItems: [{
                    index: "1",
                    name: "业主管理",
                    children: [{
                        index: "owner",
                        name: "人员信息"
                    }, {
                        index: "car",
                        name: "车辆信息"
                    }, {
                        index: "pet",
                        name: "宠物信息"
                    }, ]
                }, {
                    index: "2",
                    name: "楼栋管理",
                    children: [{
                        index: "building",
                        name: "楼栋信息"
                    }, {
                        index: "house",
                        name: "房屋信息"
                    }, {
                        index: "lease",
                        name: "租赁信息"
                    }, {
                        index: "property",
                        name: "房产信息"
                    }, ]
                }, {
                    index: "3",
                    name: "服务管理",
                    children: [{
                        index: "activity",
                        name: "活动信息"
                    }, {
                        index: "repair",
                        name: "报修信息"
                    }, {
                        index: "complaint",
                        name: "投诉信息"
                    }]
                }, {
                    index: "4",
                    name: "收费管理",
                    children: [{
                        index: "chargetype",
                        name: "收费类型"
                    }, {
                        index: "charge",
                        name: "项目信息"
                    }]
                }, {
                    index: "5",
                    name: "人力管理",
                    children: [{
                        index: "departmenttype",
                        name: "部门类型"
                    }, {
                        index: "manpower",
                        name: "职员信息"
                    }]
                }, {
                    index: "6",
                    name: "资产管理",
                    children: [{
                        index: "resources",
                        name: "资产信息"
                    }]
                }, {
                    index: "7",
                    name: "权限管理",
                    children: [{
                        index: "manager",
                        name: "管理员信息"
                    }]
                }]
            }
        },
        components: {

        },
        //向父组件抛出的函数和数据
        emits:['toTab','clickIndex'],
        props:['current'],
        setup() {
            return {

            }
        },
        updated() {
          this.currentPath=this.current  
        },
        methods: {
            totab(index, name) {
                this.currentName=name
                this.currentIndex=index
                 console.log(index, name)
            },
            toTab(){
                
            },
            clickIndex(){
                this.currentName='首页'
                this.currentIndex='index'
            }
        },
    }
</script>

<style scoped lang="less">
    .aside {
        overflow: hidden;

        .el-menu {
            background-color: #e9e3ff;
            min-height: 92vh;
            font-weight: 800;

            span {
                font-size: 16px;
                color: #4e4c55;

            }


            .el-menu-item {
                font-size: 16px;
            }

        }
    }
</style>
