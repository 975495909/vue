<template>
    <div class="header">
        <!-- 图标LOGO -->
        <div class="logo">社区物业管理系统</div>
        <!-- 头部菜单 -->
        <el-dropdown>
            <span class="el-dropdown-link">
                <span style="font-size: 16px;position: relative;bottom: 13px;">{{user}}</span>
                <el-avatar style="relative;margin-left: 25px;margin-top: 12px;" :src="url+userpic" />
                <el-icon class="el-icon--right">
                    <arrow-down />
                </el-icon>
            </span>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item @click="$emit('toTab')">个人中心</el-dropdown-item>
                    <el-dropdown-item divided @click="quit">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>

    </div>

</template>

<script>
    import axios from '@/utils/request'
    export default {
        name: "Header",
        //向父组件抛出的函数和数据
        emits: ['toTab'],
        data() {
            return {
                user: sessionStorage.getItem('user'),
                url: 'http://localhost:8088',
                userpic: '',
                User: {
                    "username": sessionStorage.getItem('user')
                }
            }
        },
        created() {
            console.log(this.User)
            this.userPic()
        },
        methods: {
            //获取头像
            userPic() {
                axios.post("/manager/selpic", this.User).then((res) => {
                    this.userpic = res.data.picture
                    console.log(res)
                })
            },
            //退出登录
            quit() {
                //清楚user和目录的本地存储
                sessionStorage.removeItem('user')
                sessionStorage.removeItem('sessTabs')
                sessionStorage.removeItem('sessTabsValue')
                //跳转登录页
                this.$router.push({
                    name: 'login',
                })
                this.$message.success('已退出！')
            },
            toTab() {
                
            },
        }
    }
</script>

<style scoped lang="less">
    .header {
        display: flex;
        justify-content: space-between;
        height: 100%;
        overflow: hidden;

        .logo {
            height: 100px;
            line-height: 60px;
            font-size: 24px;
            color: #5b5862;
            font-weight: 800;
        }

        .el-dropdown {
            height: 50px;
            line-height: 50px;
        }
    }
</style>
