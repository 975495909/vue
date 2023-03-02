<template>
    <div class="login">

        <!-- 登录盒子 -->

        <div class="login_box">
            <div class="login_head">
                <h1>社区物业管理系统</h1>
            </div>
            <el-form size="large" label-width="110px" ref="loginForm" :rules="rules" :model="loginForm"
                :hide-required-asterisk="true">
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" v-model="loginForm.username" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" v-model="loginForm.password" show-password />
                </el-form-item>
                <el-checkbox v-model="checked">记住我</el-checkbox>


                <el-button @click="login_sub">登 录</el-button>

            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from '@/utils/request';
    import Cookies from 'js-cookie'
    import {
        User,
        Lock
    } from '@element-plus/icons-vue'
    export default {
        name: "Login",
        setup() {
            return {
                User,
                Lock,
            }

        },
        data() {
            return {
                loginForm: {
                    "username": '',
                    "password": '',
                },
                labelPosition: 'Right',
                rules: {
                    username: [{
                        required: true,
                        message: '请输入用户名',
                        trigger: 'blur'
                    }],
                    password: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }]
                },
                checked: false
            }

        },
        created() {
            // 获取指定名称的cookie
            const username = Cookies.get('username')
            const password = Cookies.get('password')
            const username1 = localStorage.getItem('user')
            //会话存储有数据
            if (username1) {
                this.loginForm.username = username1
            }
            //Cookie存有数据
            if (username) {
                this.loginForm.username = username
                this.loginForm.password = password
            }
        },
        methods: {
            login_sub() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {

                        axios.post("/manager/selone", this.loginForm).then((res) => {
                            if (res.code == 200) {
                                //将管理员本地存储
                                sessionStorage.user = this.loginForm.username
                                localStorage.user = this.loginForm.username
                                //选择记住
                                if (this.checked) {
                                    // 创建一个有效时间为7天的cookie
                                    Cookies.set("username", this.loginForm.username, {
                                        expires: 7
                                    })
                                    Cookies.set("password", this.loginForm.password, {
                                        expires: 7
                                    })
                                } else {
                                    // 删除指定名称的cookie
                                    Cookies.remove("username")
                                    Cookies.remove("password")
                                }

                                //跳转首页
                                this.$router.push({
                                    name: '首页',
                                })
                                this.$message({
                                    message: res.message,
                                    type: 'success'
                                });
                            } else {
                                this.$message({
                                    message: res.message,
                                    type: 'error'
                                });
                            }
                        })
                    } else {
                        this.$message.error("请继续输入！")
                        return false
                    }
                })

            }
        },
    }
</script>

<style scoped lang="less">
    .login {
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #dbd3eb;

        .login_box {

            width: 550px;
            height: 400px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            background-color: #f5efff;
            border-radius: 3%;
            box-shadow: 3px 3px 10px 1px #beb7cc,
                -3px 3px 10px 1px #beb7cc,
                3px -3px 10px 1px #beb7cc,
                -3px -3px 10px 1px #beb7cc;

            .login_head {
                flex: 0.5;
                text-align: center;
                color: #5b5862;
            }

            .el-form {
                max-width: 430px;
                display: flex;
                flex-direction: column;

                .el-button {
                    width: 150px;
                    font-size: 15px;
                    position: relative;
                    left: 47%;
                }

                .el-checkbox {
                    left: 26%;
                    width: 0px;
                    background-color: #fff;
                }

                .el-form-item:nth-of-type(3) {
                    margin-bottom: 6px;
                }

            }
        }
    }
</style>
