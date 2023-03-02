<template>

    <div class="layout">
        <div class="common-layout">
            <el-container>
                <!-- 头部 -->
                <el-header>
                    <Header @toTab="toTab1()" />
                </el-header>

                <el-container v-show="user">
                    <!-- 侧边栏 -->
                    <el-aside width="13%" >
                        <!-- ref关联Aside子组件，@toTab和@clickIndex是子组件抛出的函数，current是子组件接收父组件数据 -->
                        <Aside ref="Aside" @toTab="toTab()" @clickIndex="clickIndex()" :current="tabIndex" />
                    </el-aside>
                    <!-- 主体 -->
                    <el-main>
                        <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab"
                            @tab-click="tab_click">
                            <el-tab-pane v-for="(item, index) in editableTabs" :key="item.name" :label="item.title"
                                :name="item.name">
                                {{item.content}}
                            </el-tab-pane>
                        </el-tabs>
                        <Content />
                    </el-main>
                </el-container>
                <el-container v-show="user==null">
                    <!-- 主体 -->
                    <Index >
                        xx
                    </Index>
                </el-container>
            </el-container>
        </div>
    </div>
</template>

<script>
    import Header from "@/components/layout/Header";
    import Aside from "@/components/layout/Aside";
    import Content from "@/layout/Content.vue";
    import Index from '../views/content/Index.vue';



    export default {
        name: "Layout",
        components: {
            Header,
            Aside,
            Content,
            Index
        },
        data() {
            return {
                user: sessionStorage.getItem('user'),
                editableTabsValue: '',
                editableTabs: [{
                    name: '首页',
                    title: '首页',
                    path: 'index',
                }],
                //当前的标签名和地址
                tabName: '首页',
                tabIndex: 'index',
            }
        },
        created() {
            
            
            //判断sessTabs是否为空
            if (sessionStorage.getItem('sessTabs')) {
                this.editableTabs = JSON.parse(sessionStorage.getItem('sessTabs'))
                this.editableTabsValue = this.$router.currentRoute.value.name
            }

        },
        destoryed() {
            //清除sessionStorage数据
            sessionStorage.clear()
        },
        methods: {
            // e() {
            //     //获取子组件的数据
            //     this.$nextTick(() => {
            //         // console.log(this.$refs["Aside"].currentIndex + this.$refs["Aside"].currentName)
            //     })
            // },
            //添加 tabs 标签页 
            toTab(name, index) {
                if (name && index) {
                    this.tabName = name
                    this.tabIndex = index

                } else {
                    this.tabName = this.$refs["Aside"].currentName
                    this.tabIndex = this.$refs["Aside"].currentIndex
                }

                //console.log(this.tabName, this.tabIndex)
                //添加之前需要 判断是否已经包含，
                let isHave = false;
                for (let i = 0; i < this.editableTabs.length; i++) {
                    if (this.editableTabs[i].title == this.tabName) {

                        //存在，包含
                        isHave = true;
                        this.editableTabsValue = this.editableTabs[i].name;
                    }

                }
                if (isHave == false) {
                    this.editableTabs.push({
                        title: this.tabName,
                        name: this.tabName,
                        // content: this.tabName + this.tabIndex , 
                        path: this.tabIndex,
                    });
                    this.editableTabsValue =
                        this.editableTabs[this.editableTabs.length - 1].name;
                }
                //本地存储值
                sessionStorage.sessTabs = JSON.stringify(this.editableTabs);
                sessionStorage.sessTabsValue = JSON.stringify(this.editableTabsValue);

                localStorage.currentPathName = JSON.stringify(this.editableTabsValue);
                // console.log(sessionStorage.sessTabs);
                // console.log(sessionStorage.sessTabsValue);
            },
            //点击 tabs 标签，跳转相应路由，选中状态
            tab_click(tab) {
                let url = this.editableTabs[tab.index].path;
                if (this.$router.currentRoute.name !== url) {
                    this.tabIndex = url;
                    this.$router.push(url);

                }
                //存储选中状态

                if (url == 'personal') this.toTab1(this.editableTabs[tab.index].title, url);
                else this.toTab(this.editableTabs[tab.index].title, url);
            },
            //移除tabs 标签
            removeTab(targetName) {
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                //关闭当前页
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            //下一个 next 打开的页面，有就下一个，没就上一个
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                                this.tabIndex = nextTab.path;
                                this.$router.push(nextTab.path);
                            }
                        }
                    });
                }
                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter((tab) => tab.name !== targetName);
                //本地存储值
                sessionStorage.sessTabs = JSON.stringify(this.editableTabs);
                sessionStorage.sessTabsValue = JSON.stringify(this.editableTabsValue);
            },
            //点击首页的问题解决
            clickIndex() {
                this.tabName = '首页'
                this.tabIndex = 'index'
                let isHave = false;
                for (let i = 0; i < this.editableTabs.length; i++) {
                    if (this.editableTabs[i].title == this.tabName) {

                        //存在，包含
                        isHave = true;
                        this.editableTabsValue = this.editableTabs[i].name;
                    }
                }
                if (isHave == false) {
                    this.editableTabs.push({
                        title: this.tabName,
                        name: this.tabName,
                        // content: this.tabName + this.tabIndex , 
                        path: this.tabIndex,
                    });
                    this.editableTabsValue =
                        this.editableTabs[this.editableTabs.length - 1].name;
                }
                //本地存储值
                sessionStorage.sessTabs = JSON.stringify(this.editableTabs);
                sessionStorage.sessTabsValue = JSON.stringify(this.editableTabsValue);
            },
            toTab1() {
                //跳转个人中心
                this.$router.push({
                    name: '个人中心',
                })
                this.tabName = '个人中心'
                this.tabIndex = 'personal'
                //添加之前需要 判断是否已经包含，
                let isHave = false;
                for (let i = 0; i < this.editableTabs.length; i++) {
                    if (this.editableTabs[i].title == this.tabName) {

                        //存在，包含
                        isHave = true;
                        this.editableTabsValue = this.editableTabs[i].name;
                    }

                }
                if (isHave == false) {
                    this.editableTabs.push({
                        title: this.tabName,
                        name: this.tabName,
                        // content: this.tabName + this.tabIndex , 
                        path: this.tabIndex,
                    });
                    this.editableTabsValue =
                        this.editableTabs[this.editableTabs.length - 1].name;
                }
                //本地存储值
                sessionStorage.sessTabs = JSON.stringify(this.editableTabs);
                sessionStorage.sessTabsValue = JSON.stringify(this.editableTabsValue);

                localStorage.currentPathName = JSON.stringify(this.editableTabsValue);
            },
        }
    }
</script>

<style scoped lang="less">
    .layout {
        height-max: 100hv;
    }

    .el-header {
        background-color: #b1a4d9;
    }

    .el-aside {
        height: 92vh;
    }

    /* //::v-deep 作用是样式穿透,要在scoped作用下才可使用 */

    /* // 设置滚动条宽度 */
    ::v-deep .el-aside::-webkit-scrollbar {
        width: 6px !important;
        height: 6px !important;
        background: rgba(10, 122, 227, 0.01);
    }

    /* //滚动条的滑块 */
    ::-webkit-scrollbar-thumb {
        background: #b1a4d9;
        border-radius: 3px;
    }
</style>
