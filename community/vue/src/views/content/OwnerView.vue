<template>
    <div class="owner">
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
                        <el-dialog v-model="dialogFormVisible" :title="diaTitle" width="60%" @close="formClose">
                            <el-form :model="form" :rules="rules" label-width="30%" ref="form">
                                <!-- <el-form-item label="楼栋名称" prop="buildingName">
                                    <el-select v-model="form.buildingId" clearable filterable placeholder=" "
                                        @change="selected()" @clear="clear()">
                                        <el-option v-for="item in options" :key="item.id" :label="item.name"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="房间号" prop="houseNum">
                                    <el-select v-model="form.houseId" clearable filterable placeholder=" "
                                        @change="selected2()" :disabled="form.buildingId==null?true:false">
                                        <el-option v-for="item in options2" :key="item.id" :label="item.houseNum"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="类型" prop="type">
                                    <el-radio-group v-model="form.type">
                                        <el-radio label="0">房主</el-radio>
                                        <el-radio label="1">租客</el-radio>
                                    </el-radio-group>
                                </el-form-item> -->
                                <el-form-item label="姓名" prop="name">
                                    <el-input v-model="form.name" clearable />
                                </el-form-item>
                                <el-form-item label="性别" prop="sex">
                                    <el-radio-group v-model="form.sex">
                                        <el-radio label="男">男</el-radio>
                                        <el-radio label="女">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item label="手机号" prop="telephone">
                                    <el-input v-model="form.telephone" clearable />
                                </el-form-item>
                                <el-form-item label="身份证号" prop="idcard">
                                    <el-input v-model="form.idcard" clearable />
                                </el-form-item>
                                <!-- <el-form-item label="入住日期" prop="liveTime">
                                    <el-date-picker v-model="form.liveTime" type="date" placeholder="选择日期" />
                                </el-form-item> -->
                                <el-form-item label="职业" prop="profession">
                                    <el-input v-model="form.profession" clearable />
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
                        <el-input class="inpSearch" v-model="searchMap.selcontent" placeholder="请输入"
                            :prefix-icon="Search" clearable @keyup.enter.native="searchMap.pageNum=1;search()">
                            <template #append>
                                <el-select v-model="searchMap.sel" style="width: 150px;background-color: #f3efff;">
                                    <el-option label="楼栋名称" value="buildingName" />
                                    <el-option label="房间号" value="houseNum" />
                                    <el-option label="姓名" value="name" />
                                </el-select>
                            </template>
                        </el-input>
                        <!-- 日期选择器 -->
                        <div class="date">
                            <el-date-picker v-model="selectDate" type="daterange" range-separator="到"
                                start-placeholder="开始时间" end-placeholder="结束时间" size="large" @change="dateChange" />
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
                        <el-table-column prop="houseNum" label="房间号" width="130" />
                        <el-table-column prop="name" label="姓名" width="130" />
                        <el-table-column prop="sex" label="性别" width="80" />
                        <el-table-column prop="telephone" label="手机号" width="200" />
                        <el-table-column prop="profession" label="职业" width="200" />
                        <el-table-column prop="idcard" label="身份证号" width="300">
                            <template v-slot="scope">
                                <span v-if="List[scope.$index].idcLook==true">{{scope.row.idcard}}</span>
                                <span v-else>**********</span>
                                <el-icon size="large" style="float: right;cursor: pointer;" @click="look(scope.$index)">
                                    <component :is="View"></component>
                                </el-icon>
                            </template>
                        </el-table-column>
                        <el-table-column prop="liveTime" label="入住时间" width="180" />
                        <el-table-column prop="remark" label="备注" width="526" />
                        <el-table-column prop="createTime" label="创建时间" width="180" sortable />
                        <el-table-column fixed="right" prop="type" label="状态" width="130" sortable><template
                                #default="scope">
                                <el-tag :type="scope.row.type== '0' ? '': scope.row.type== '1'?'warning':'success'">
                                    {{scope.row.type== '0' ? '无' : scope.row.type== '1'?'房主':'租客'}}
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
        CirclePlus,
        Plus,
        View
    } from '@element-plus/icons-vue'

    export default {
        name: 'OwnerView',
        components: {},
        inject: ['reload'],
        setup() {
            return {
                // 图标导入
                Search,
                Delete,
                Edit,
                CirclePlus,
                Plus,
                View
            }
        },
        data() {

            return {
                url: 'http://localhost:8088',
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
                    name: [{
                        required: true,
                        max: 255,
                        message: '请输入业主姓名',
                        trigger: 'blur'
                    }],
                    remark: [{
                        required: false,
                        max: 500,
                        message: '该内容500字以内',
                        trigger: 'blur'
                    }],
                    buildingName: [{
                        required: true,
                        max: 255,
                        message: '请输入楼栋名称',
                        trigger: 'blur'
                    }],
                    houseNum: [{
                        required: true,
                        message: '请输入房间号',
                        trigger: 'blur'
                    }],
                    sex: [{
                        required: true,
                        message: '请输入性别',
                        trigger: 'blur'
                    }],
                    type: [{
                        required: true,
                        message: '请选择类型',
                        trigger: 'blur'
                    }],
                    idcard: [{
                        required: true,
                        min:18,
                        max: 18,
                        message: '请输入格式正确的身份证号',
                        trigger: 'blur'
                    }],
                    telephone: [{
                        required: true,
                        max: 11,
                        min: 11,
                        message: '请输入格式正确的手机号',
                        trigger: 'blur'
                    }],
                    profession: [{
                        required: false,
                        max: 255,
                        message: '请输入职业',
                        trigger: 'blur'
                    }],
                    liveTime: [{
                        required: true,
                        message: '请输入入住时间',
                        trigger: 'blur'
                    }],
                },
                // 删除的id
                delId: '',
                //批量删除的id
                delIds: [],
                //select选择数据
                options: [],
                //上传头像的前端地址
                imageUrlF: '',
                //上传头像的后端地址
                // imageUrlB: '',
                //上传头像的地址们，方便删除没有提交的图片数据
                imagesUrl: [],
                //预览图片数据
                srcList: [],
                //上传图片
                actionImg: '/file/owner',
                options2: [],
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
                axios.post("/owner/selpage", this.searchMap).then((res) => {
                    // console.log(this.searchMap)
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.List = res.data
                        this.total = res.total
                        console.log(res)

                        //调整属性status的状态
                        res.data.forEach(item => {
                            //获取houseId
                            const Id = JSON.parse(JSON.stringify(item)).id
                            if (Id != null) {

                                axios.get("/property/selbyownerid?id=" + Id).then((
                                    res) => {
                                    //租客2
                                    if (res.data.length != 0) {
                                        item.type = 1
                                        if (item.buildingId == 0) {
                                            item.buildingId = res.data[0].buildingId
                                            item.buildingName = res.data[0].buildingName
                                            item.houseId = res.data[0].houseId
                                            item.houseNum = res.data[0].houseNum
                                            item.liveTime = res.data[0].liveTime
                                            axios.post("/owner/update", item).then((res) => {

                                            })
                                        }
                                    } else {
                                        axios.get("/lease/selbyownerid?id=" + Id).then((
                                            res) => {
                                            //房主1
                                            if (res.data.length != 0) {
                                                item.type = 2
                                                if (item.buildingId == 0) {
                                                    item.buildingId = res.data[0]
                                                        .buildingId
                                                    item.buildingName = res.data[0]
                                                        .buildingName
                                                    item.houseId = res.data[0].houseId
                                                    item.houseNum = res.data[0].houseNum
                                                    item.liveTime = res.data[0]
                                                        .signingTime
                                                    console.log(item)
                                                    axios.post("/owner/update", item)
                                                        .then((res) => {})
                                                }
                                            }
                                            // 无0
                                            else {
                                                item.type = 0
                                                if (item.buildingId != 0) {
                                                    item.buildingId = 0
                                                    item.buildingName = '无'
                                                    item.houseId = 0
                                                    item.houseNum = '无'
                                                    item.liveTime =
                                                        '1970-01-01 10:00:00'
                                                    axios.post("/owner/update", item)
                                                        .then((
                                                            res) => {})
                                                }
                                            }
                                        })
                                    }
                                })

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
                    axios.get("/owner/selbyid?id=" + id).then((res) => {
                        this.form = res.data;
                        //选择器
                        this.searchOptions2()
                    });
                }
                this.dialogFormVisible = true
            },
            //表单保存
            handleSave() {
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
                        axios.post("/owner/" + method, obj).then((res) => {
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
                    axios.post("/owner/del", this.delIds).then((res) => {
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
                axios.post("/owner/del", this.delIds).then((res) => {
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
                axios.get("/owner/selall").then((res) => {
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.options = res.data
                    }
                })
            },
            //类型过滤
            typeFormat(row, column) {
                return row.type == '0' ? '房主' : '租客'
            },
            //是否查看身份证号
            look(index) {
                if (this.List[index].idcLook == true) {
                    this.List[index].idcLook = false
                } else {
                    this.List[index].idcLook = true
                }
            },
            //选择器的数据
            searchOptions() {
                axios.get("/building/selall").then((res) => {
                    // console.log(this.searchMap)
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.options = res.data
                        // console.log(res)
                    }
                })
            },
            //选择器的数据
            searchOptions2() {
                if (this.form.buildingId != null)
                    axios.get("/house/selbybuildingid?id=" + this.form.buildingId).then((res) => {
                        if (res == null || res.length == 0) this.loading = true
                        else {
                            this.loading = false
                            this.options2 = res.data
                            console.log(res.data)
                        }
                    })
            },
            //选择器被选择，根据id查找，完成form数据完整
            selected2() {
                axios.get("/house/selbyid?id=" + this.form.houseId).then((res) => {
                    this.form.houseNum = res.data.houseNum
                });

            },
            //选择器被选择，根据id查找，完成form数据完整
            selected() {
                axios.get("/building/selbyid?id=" + this.form.buildingId).then((res) => {
                    this.form.buildingName = res.data.name
                });
                this.searchOptions2()
            },
            //选择器中清空的情况，后面数据跟着清空
            clear() {
                this.options2 = []
                this.form.buildingId = null
                this.form.houseNum = ''
                this.form.houseId = null
            },
        }
    }
</script>

<style scoped src="@/assets/css/content.css">


</style>
