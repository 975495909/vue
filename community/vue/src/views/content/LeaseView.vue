<template>
    <div class="lease">
        <div class="common-layout">
            <el-container>
                <!-- 头部 -->
                <el-header>
                    <div class="head">
                        <!-- 批量删除按钮 -->
                        <el-button type="primary" plain @click="handleDel" :icon="Delete">批量删除</el-button>
                        <!-- 添加数据按钮 -->
                        <el-button type="primary" plain @click="handleDialog(1)" :icon="CirclePlus">添加</el-button>
                        <!-- 弹窗盒子 -->
                        <el-dialog v-model="dialogFormVisible" :title="diaTitle" width="60%">
                            <el-form :model="form" :rules="rules" label-width="30%" ref="form">
                                <el-form-item label="楼栋名称" prop="buildingName">
                                    <el-select v-model="form.buildingId" clearable filterable placeholder=" "
                                        @change="selected()" @clear="clear()">
                                        <el-option v-for="item in options" :key="item.id" :label="item.name"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="业主姓名" prop="ownerName">
                                    <el-select v-model="form.ownerId" clearable filterable placeholder=" "
                                        @change="selected2()">
                                        <el-option v-for="item in options2" :key="item.id" :label="item.name"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="房间号" prop="houseNum">
                                    <el-select v-model="form.houseId" clearable filterable placeholder=" "
                                        @change="selected3()" :disabled="form.buildingId==null?true:false">
                                        <el-option v-for="item in options3" :key="item.id" :label="item.houseNum"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="业务员名称" prop="salesmanName">
                                    <el-select v-model="form.salesmanId" clearable filterable placeholder=" "
                                        @change="selected4()">
                                        <el-option v-for="item in options4" :key="item.id" :label="item.name"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="租期(月)" prop="leaseTerm">
                                    <el-input v-model="form.leaseTerm" clearable />
                                </el-form-item>
                                <el-form-item label="月租金(¥)" prop="monthlyRent">
                                    <el-input v-model="form.monthlyRent" clearable />
                                </el-form-item>
                                <el-form-item label="起租日期" prop="startingTime">
                                    <el-date-picker v-model="form.startingTime" type="date" placeholder="选择日期" />
                                </el-form-item>
                                <el-form-item label="签订日期" prop="signingDate">
                                    <el-date-picker v-model="form.signingDate" type="date" placeholder="选择日期" />
                                </el-form-item>
                                <el-form-item label="备注" prop="remark">
                                    <el-input v-model="form.remark" type="textarea" :rows="5" />
                                </el-form-item>
                            </el-form>
                            <template #footer>
                                <span class="dialog-footer">
                                    <el-button @click="dialogFormVisible = false">关闭</el-button>
                                    <el-button type="primary" @click="handleSave()">
                                        提交
                                    </el-button>
                                </span>
                            </template>
                        </el-dialog>

                        <!-- 查询输入框 -->
                        <!--                        <el-input class="inpSearch" v-model="searchMap.name" placeholder="搜索部门类型名称"
                            :prefix-icon="Search" clearable @keyup.enter.native="searchMap.pageNum=1;search()" /> -->
                        <el-input class="inpSearch" v-model="searchMap.selcontent" placeholder="请输入"
                            :prefix-icon="Search" clearable @keyup.enter.native="searchMap.pageNum=1;search()">
                            <template #append>
                                <el-select v-model="searchMap.sel" style="width: 150px;background-color: #f3efff;">
                                    <el-option label="楼栋名称" value="buildingName" />
                                    <el-option label="业主姓名" value="ownerName" />
                                    <el-option label="房间号" value="houseNum" />
                                    <el-option label="业务员名称" value="salesmanName" />
                                </el-select>
                            </template>
                        </el-input>
                        <!-- 日期选择器 -->
                        <div class="date">
                            <el-date-picker v-model="selectDate" type="daterange" range-separator="到"
                                start-placeholder="开始时间" end-placeholder="结束时间" size="large" @change="dateChange"
                                value-format="YYYY-MM-DD hh:mm:ss" />
                        </div>
                        <!-- 查询按钮 -->
                        <el-button type="primary" plain @click="searchMap.pageNum=1;search()" :icon="Search">查询
                        </el-button>
                    </div>
                </el-header>
                <!-- 主体 -->
                <el-main>
                    <!-- 表格 -->
                    <el-table v-loading="loading" element-loading-text="拼命加载中..." :data="List" border size="large"
                        @selection-change="handleSelectionChange" :header-cell-style="{
                            'background-color': '#d4cdf0',
                            'color': '#877fa6',
                            'border-bottom': '1px #877fa6 solid'
                        }" :default-sort="{prop: 'id', order: 'ascending'}">
                        <el-table-column width="50" type="selection" />
                        <el-table-column fixed prop="id" label="ID" width="80" sortable />
                        <el-table-column prop="buildingName" label="楼栋名称" width="300" />
                        <el-table-column prop="ownerName" label="业主姓名" width="130" />
                        <el-table-column prop="houseNum" label="房间号" width="130" />
                        <el-table-column prop="salesmanName" label="业务员姓名" width="130" />
                        <el-table-column prop="leaseTerm" label="租期(月)" width="130" />
                        <el-table-column prop="monthlyRent" label="月租金(¥)" width="130" />
                        <el-table-column prop="startingTime" label="起租日期" width="180" />
                        <el-table-column prop="signingDate" label="签订日期" width="180" />
                        <el-table-column prop="remark" label="备注" width="526" />
                        <el-table-column prop="createTime" label="创建时间" width="180" sortable />
                        <el-table-column fixed="right" prop="status" label="状态" width="130" sortable><template
                                #default="scope">
                                <el-tag :type="scope.row.status== '0' ? '': scope.row.status== '1'?'success':'danger'">
                                    {{scope.row.status== '0' ? '未启动' : scope.row.status== '1'?'合约期内':'合约到期'}}
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="120">
                            <template #default="scope">
                                <!-- 编辑按钮 -->
                                <el-button class="btnEsit" link @click="handleDialog(2,scope.row)" :icon="Edit">
                                </el-button>
                                <!-- 删除按钮 -->
                                <el-button class="btnDel" link @click="delId=scope.row;dialogVisible = true;"
                                    :icon="Delete">
                                </el-button>

                                <!--确认删除弹框-->
                                <el-dialog v-model="dialogVisible" title="" width="30%" :modal="false"
                                    :show-close="false">
                                    <span>确定要删除该内容？</span>
                                    <template #footer>
                                        <span class="dialog-footer">
                                            <el-button @click="dialogVisible = false">关闭</el-button>
                                            <el-button type="primary" @click="handleDelete()">
                                                确认
                                            </el-button>
                                        </span>
                                    </template>
                                </el-dialog>

                            </template>
                        </el-table-column>
                    </el-table>
                </el-main>
                <!-- 尾部 -->
                <el-footer>
                    <!-- 分页 -->
                    <el-pagination v-model:currentPage="currentPage" v-model:page-size="searchMap.pageSize"
                        :page-sizes="[5,10,20,50]" layout="total, sizes, prev, pager, next, jumper" :total="total"
                        @size-change="handleSizeChange" @current-change="handleCurrentChange" />
                </el-footer>
            </el-container>
        </div>
    </div>
</template>


<script>
    import axios from '@/utils/request'
    import {
        Search,
        Delete,
        Edit,
        CirclePlus
    } from '@element-plus/icons-vue'

    export default {
        name: 'LeaseView',
        components: {},
        inject: ['reload'],
        setup() {
            return {
                // 图标导入
                Search,
                Delete,
                Edit,
                CirclePlus
            }
        },
        data() {

            return {
                loading: true,
                //数据
                List: [],
                //多选数据
                multipleSelection: [],
                //日期选择器
                selectDate: ['', ''],
                //传入后台数据
                searchMap: {
                    "startTime": "",
                    "endTime": "",
                    "sel": "",
                    "selcontent": "",
                    "pageNum": 1,
                    "pageSize": 10
                },
                //数据总数量初始
                total: 10,
                //当前页码
                currentPage: 1,
                // 是否弹出表单框
                dialogFormVisible: false,
                //表单内容
                form: {},
                //弹框标题
                diaTitle: '',
                //删除确认对话框
                dialogVisible: false,
                //输入规则
                rules: {
                    buildingName: [{
                        required: true,
                        max: 255,
                        message: '请输入楼栋名称',
                        trigger: 'blur'
                    }],
                    ownerName: [{
                        required: true,
                        max: 255,
                        message: '请输入业主姓名',
                        trigger: 'blur'
                    }],
                    signingDate: [{
                        required: true,
                        message: '请输入签订时间',
                        trigger: 'blur'
                    }],
                    houseNum: [{
                        required: true,
                        message: '请输入房间号',
                        trigger: 'blur'
                    }],
                    leaseTerm: [{
                        required: true,
                        message: '请输入租金',
                        trigger: 'blur'
                    }],
                    monthlyRent: [{
                        required: true,
                        message: '请输入月租金',
                        trigger: 'blur'
                    }],
                    salesmanName: [{
                        required: true,
                        max: 255,
                        message: '请输入业务员名称',
                        trigger: 'blur'
                    }],
                    startingTime: [{
                        required: true,
                        message: '请输入起租日期',
                        trigger: 'blur'
                    }],
                    remark: [{
                        required: false,
                        max: 500,
                        message: '该内容500字以内',
                        trigger: 'blur'
                    }],
                },
                // 删除的id
                delId: '',
                //批量删除的id
                delIds: [],
                options2: [],
                options3: [],
            }
        },
        created() {
            this.search()
        },

        mounted() {
            //加载出查询sel的内容
            this.searchOptions()

        },

        methods: {
            //分页+搜索查看
            search() {
                axios.post("/lease/selpage", this.searchMap).then((res) => {
                    // console.log(this.searchMap)
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.List = res.data
                        this.total = res.total
                        console.log(res)

                        //调整属性status的状态
                        res.data.forEach(item => {

                            //获取租期
                            const t = JSON.parse(JSON.stringify(item)).leaseTerm
                            //获取起租时间
                            const start = new Date(JSON.parse(JSON.stringify(item)).startingTime)
                            //获取现在日期
                            const now = new Date()
                            //已租时长
                            const g = (now - start) / (1000 * 60 * 60 * 24 * 30)
                            //未启动0
                            if (now < start && item.status != 0) {
                                item.status = 0
                                // console.log(item)
                                axios.post("/lease/update", item).then((res) => {

                                });
                                this.reload()

                            }
                            //合约期内1
                            else {
                                if (g <= t && item.status != 1) {
                                    item.status = 1
                                    axios.post("/lease/update", item).then((res) => {

                                    });
                                    this.reload()
                                }
                                //合约到期2
                                else if (g > t && item.status != 2) {
                                    item.status = 2
                                    axios.post("/lease/update", item).then((res) => {

                                    });
                                    this.reload()

                                }
                            }

                        })
                    }
                })
            },
            //页数大小
            handleSizeChange(pageSize) {
                this.searchMap.pageSize = pageSize;
                this.search();
                console.log('pageSize' + this.searchMap.pageSize)
            },
            //当前页数
            handleCurrentChange(pageNum) {
                this.searchMap.pageNum = pageNum;
                this.search();
                console.log('pageNum' + this.searchMap.pageNum)
            },

            //table 多选功能
            handleSelectionChange(val) {
                //选中的数据
                this.multipleSelection = val;
            },
            //打开弹窗
            handleDialog(val, row) {
                if (val == 1) {
                    this.form = []
                    this.diaTitle = '添加'
                } else {
                    // console.log(JSON.parse(JSON.stringify(row)).id)
                    this.diaTitle = '编辑'
                    //获取指定行的对象id
                    const id = JSON.parse(JSON.stringify(row)).id
                    axios.get("/lease/selbyid?id=" + id).then((res) => {
                        this.form = res.data;
                        //日期区间
                        // this.selectDate=[res.data.startingTime,res.data.endingTime]
                        //选择器
                        this.searchOptions2()
                        this.searchOptions3()
                    });
                }
                this.dialogFormVisible = true
            },
            //表单保存
            handleSave() {
                console.log(this.form)
                var method = ''
                var obj = {}
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        if (this.diaTitle == '添加') {
                            method = 'add'
                        } else {
                            method = 'update'
                        }
                        obj = {
                            ...this.form
                        }
                        // console.log(obj)
                        axios.post("/lease/" + method, obj).then((res) => {
                            if (res.flag) {
                                this.$message({
                                    message: res.message,
                                    type: 'success'
                                });
                            } else {
                                this.$message({
                                    message: '添加失败！',
                                    type: 'error'
                                });
                            }
                            this.reload()
                        });

                        this.reload() // 需要刷新页面


                        // this.reload() // 需要刷新页面
                        this.selectDate = []
                        console.log("成功");
                    } else {
                        console.log("失败");
                        return false;
                    }
                })


                // this.$router.go(0)
                // this.dialogFormVisible = false
            },
            //批量删除
            handleDel() {
                const dataDel = this.multipleSelection
                var id
                //获取选中数量
                // console.log(dataDel.length)
                if (dataDel.length != 0) {
                    dataDel.forEach(item => {
                        //获取元素 
                        // console.log(item)
                        // console.log(JSON.parse(JSON.stringify(item)).id)
                        id = JSON.parse(JSON.stringify(item)).id
                        this.delIds.push(id)
                    })
                    axios.post("/lease/del", this.delIds).then((res) => {
                        if (res.flag) {
                            this.$message({
                                message: res.message,
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '删除失败！',
                                type: 'error'
                            });
                        }
                        this.reload()
                    });
                    this.reload()
                    this.dialogVisible = false;
                    this.delIds = []
                    this.delId = ''
                }
                //未选择
                else {
                    this.$message({
                        message: '未选中！',
                        type: 'warning'
                    });
                }
            },
            //删除
            handleDelete() {
                const id = JSON.parse(JSON.stringify(this.delId)).id
                this.delIds.push(id)
                axios.post("/lease/del", this.delIds).then((res) => {
                    if (res.flag) {
                        this.$message({
                            message: res.message,
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            message: '删除失败！',
                            type: 'error'
                        });
                    }
                    this.reload()
                });
                this.reload()
                this.dialogVisible = false;
                this.delIds = []
                this.delId = ''
            },

            //时间区间发生改变，searcHMap中时间区间条件也要变化
            dateChange() {
                //没有选择时间区间或者清理时间条件，当前时间区间为空字符串
                const daterange = JSON.parse(JSON.stringify(this.selectDate))
                console.log(daterange[0])
                if (daterange == null || daterange.length == 0) {
                    this.searchMap.startTime = '';
                    this.searchMap.endTime = '';
                } else {
                    //当时间区间不为空，往searchMap中添加条件
                    if (daterange[0] != null && daterange[0] != '') {
                        this.searchMap.startTime = daterange[0];
                    }
                    if (daterange[1] != null && daterange[1] != '') {
                        this.searchMap.endTime = daterange[1];
                    }
                }

            },

            //选择器的数据
            searchOptions() {
                axios.get("/building/selall").then((res) => {
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.options = res.data
                        // console.log(res.data)
                    }
                })
                axios.get("/manager/selall").then((res) => {
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.options4 = res.data
                        // console.log(res.data)
                    }
                })

            },
            //选择器的数据
            searchOptions2() {
                if (this.form.buildingId != null)
                    axios.get("/owner/selall").then((res) => {
                        if (res == null || res.length == 0) this.loading = true
                        else {
                            this.loading = false
                            this.options2 = res.data
                            // console.log(res.data)
                        }
                    })
            },
            //选择器的数据
            searchOptions3() {
                if (this.form.buildingId != null)
                    axios.get("/house/selbybuildingid?id=" + this.form.buildingId).then((res) => {
                        if (res == null || res.length == 0) this.loading = true
                        else {
                            this.loading = false
                            this.options3 = res.data
                            console.log(res.data)
                        }
                    })
            },
            //选择器被选择，根据id查找，完成form数据完整
            selected() {
                axios.get("/building/selbyid?id=" + this.form.buildingId).then((res) => {
                    this.form.buildingName = res.data.name
                })
                this.searchOptions2()
                this.searchOptions3()
            },
            //选择器被选择，根据id查找，完成form数据完整
            selected2() {

                axios.get("/owner/selbyid?id=" + this.form.ownerId).then((res) => {
                    this.form.ownerName = res.data.name
                });
            },
            //选择器被选择，根据id查找，完成form数据完整
            selected3() {
                axios.get("/house/selbyid?id=" + this.form.houseId).then((res) => {
                    this.form.houseNum = res.data.houseNum
                });

            },
            //选择器被选择，根据id查找，完成form数据完整
            selected4() {
                axios.get("/manager/selbyid?id=" + this.form.salesmanId).then((res) => {
                    this.form.salesmanName = res.data.name
                });

            },
            // dateChangeForm() {
            //     //没有选择时间区间或者清理时间条件，当前时间区间为空字符串
            //     const daterange = JSON.parse(JSON.stringify(this.selectDate))
            //     if (daterange == null || daterange.length == 0) {
            //         this.form.startingTime = '';
            //         this.form.endingTime = '';
            //     } else {
            //         //当时间区间不为空，往searchMap中添加条件
            //         if (daterange[0] != null && daterange[0] != '') {
            //             this.form.startingTime = daterange[0];
            //         }
            //         if (daterange[1] != null && daterange[1] != '') {
            //             this.form.endingTime = daterange[1];
            //         }
            //     }
            // },
            //选择器中清空的情况，后面数据跟着清空
            clear() {
                // this.options2 = []
                this.form.buildingId = null
                this.form.houseNum = ''
                this.form.houseId = null
                // this.form.ownerName = ''
                // this.form.ownerId = null
            },

        }
    }
</script>

<style scoped src="@/assets/css/content.css">

</style>
