<template>
    <div class="personal">
        <el-container>
            <el-aside>
                <el-image style="width: 250px; height: 300px;bottom: 300px;position:absolute;" :src="url+User.picture"
                    fit="contain"></el-image>
            </el-aside>
            <el-main>
                <el-descriptions title="" :column="2" direction="vertical" border>
                    <el-descriptions-item label="ID">{{User.id}}</el-descriptions-item>
                    <el-descriptions-item label="类型" :span="2">{{User.type==0?'管理员':User.type==1?'超级管理员':''}}
                    </el-descriptions-item>
                    <el-descriptions-item label="用户名" :span="2">{{User.loginName}}</el-descriptions-item>
                    <el-descriptions-item label="姓名">{{User.name}}</el-descriptions-item>
                    <el-descriptions-item label="性别">{{User.sex}}</el-descriptions-item>
                    <el-descriptions-item label="手机号" :span="2">{{User.telephone}}</el-descriptions-item>
                    <el-descriptions-item label="邮箱">{{User.email}}</el-descriptions-item>
                </el-descriptions>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import axios from '@/utils/request'
    export default {
        name: "Personal",
        setup() {},

        data() {
            return {
                url: 'http://localhost:8088',
                user: sessionStorage.getItem('user'),
                User: {
                    "username": sessionStorage.getItem('user')
                },
            }

        },
        created() {

            if (this.user == null) {
                this.$message({
                    message: '请登录',
                    type: 'error'
                })
            } else {
                //展示个人信息
                axios.post("/manager/selpic", this.User).then((res) => {
                    if (res.flag) {
                        this.User = res.data
                        console.log(this.User)
                    } else {
                        this.$message({
                            message: '未获取信息',
                            type: 'error'
                        })
                    }
                })
            }
        },
        methods: {

        },
    }
</script>

<style>
    .el-descriptions__label.el-descriptions__cell.is-bordered-label {
        background: #f3efff;
    }
</style>
