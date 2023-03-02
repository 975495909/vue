<template>
    <div class="manpower">
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
                                <el-form-item label="员工姓名" prop="name">
                                    <el-input v-model="form.name" clearable />
                                </el-form-item>
                                <el-form-item label="部门名称" prop="departmentName">
                                    <el-select v-model="form.departmentId" clearable filterable placeholder=" "
                                        @change="selected()">
                                        <el-option v-for="item in options" :key="item.id" :label="item.name"
                                            :value="item.id" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="岗位" prop="job">
                                    <el-input v-model="form.job" clearable />
                                </el-form-item>
                                <el-form-item label="性别" prop="sex">
                                    <el-radio-group v-model="form.sex">
                                        <el-radio label="男">男</el-radio>
                                        <el-radio label="女">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item label="身份证号" prop="idcard">
                                    <el-input v-model="form.idcard" clearable />
                                </el-form-item>
                                <el-form-item label="手机号" prop="telephone">
                                    <el-input v-model="form.telephone" clearable />
                                </el-form-item>
                                <el-form-item label="公司名称" prop="company">
                                    <el-input v-model="form.company" clearable />
                                </el-form-item>
                                <el-form-item label="照片" prop="picture">
                                    <!-- <el-input v-model="form.picture" clearable /> -->
                                    <!-- 上传头像 -->
                                    <el-upload :drag="true" class="avatar-uploader" :action="url+actionImg"
                                        :show-file-list="false" :on-success="handleAvatarSuccess"
                                        :before-upload="beforeAvatarUpload">
                                        <el-icon class="avatar-uploader-icon">
                                            <img v-if="imageUrlF" :src="imageUrlF" class="avatar" />
                                            <el-icon class="avatar-uploader-plus">
                                                <component :is="Plus"></component>
                                            </el-icon>
                                        </el-icon>
                                    </el-upload>
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
                        <!-- <el-input class="inpSearch" v-model="searchMap.name" placeholder="搜索员工姓名" :prefix-icon="Search"
                            clearable @keyup.enter.native="searchMap.pageNum=1;search()" /> -->
                        <el-input class="inpSearch" v-model="searchMap.selcontent" placeholder="请输入"
                            :prefix-icon="Search" clearable @keyup.enter.native="searchMap.pageNum=1;search()">
                            <template #append>
                                <el-select v-model="searchMap.sel" style="width: 150px;background-color: #f3efff;">
                                    <el-option label="员工姓名" value="name" />
                                    <el-option label="部门类型名称" value="departmentName" />
                                    <el-option label="公司名称" value="company" />
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
                        <el-table-column prop="picture" label="照片" width="150">
                            <template v-slot="scope">
                                <el-image :src="url+scope.row.picture" style="width: 90px;cursor: pointer;" fit="cover"
                                    :preview-src-list="srcList" @click="imagePreview(scope.row)"
                                    :preview-teleported="true" loading="lazy" @close="imageClose">

                                </el-image>
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="员工姓名" width="130" />
                        <el-table-column prop="departmentName" label="部门名称" width="300" />
                        <el-table-column prop="job" label="岗位" width="200" />
                        <el-table-column prop="sex" label="性别" width="80" />
                        <el-table-column prop="telephone" label="手机号" width="200" />
                        <el-table-column prop="idcard" label="身份证号" width="300">
                            <template v-slot="scope">
                                <span v-if="List[scope.$index].idcLook==true">{{scope.row.idcard}}</span>
                                <span v-else>**********</span>
                                <el-icon size="large" style="float: right;cursor: pointer;" @click="look(scope.$index)">
                                    <component :is="View"></component>
                                </el-icon>
                            </template>
                        </el-table-column>
                        <el-table-column prop="company" label="公司名称" width="300" />
                        <el-table-column prop="remark" label="备注" width="526" />
                        <el-table-column prop="createTime" label="创建时间" width="180" sortable />
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
        View,
    } from '@element-plus/icons-vue'

    export default {
        name: 'BuildingView',
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
                View,
            }
        },
        data() {

            return {
                url: 'http://localhost:8088',
                loading: true,
                //数据
                List: [{
                    idcLook: false
                }],
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
                        max:255,
                        message: '请输入部门类型名称',
                        trigger: 'blur'
                    }],
                    remark: [{
                        required: false,
                        max: 500,
                        message: '该内容500字以内',
                        trigger: 'blur'
                    }],
                    departmentName: [{
                        required: true,
                        max:255,
                        message: '请输入部门名称',
                        trigger: 'blur'
                    }],
                    job: [{
                        required: true,
                        max:255,
                        message: '请输入岗位',
                        trigger: 'blur'
                    }],
                    sex: [{
                        required: true,
                        message: '请输入性别',
                        trigger: 'blur'
                    }],
                    idcard: [{
                        required: true,
                        max:18,
                        min:18,
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
                    company: [{
                        required: false,
                        max:255,
                        trigger: 'blur'
                    }],

                    picture: [{
                        required: true,
                        message: '请上传头像',
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
                actionImg: '/file/manpower',

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
                // console.log(this.searchMap)
                axios.post("/manpower/selpage", this.searchMap).then((res) => {
                    // console.log(this.searchMap)
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.List = res.data
                        this.total = res.total
                        console.log(res)
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
                    axios.get("/manpower/selbyid?id=" + id).then((res) => {
                        this.form = res.data;
                        this.imageUrl = this.url + this.form.picture
                    });


                }
                this.dialogFormVisible = true
            },
            //上传图片
            handleAvatarSuccess(res, file) {
                if (res.flag) {
                    this.$message({
                        message: res.message,
                        type: 'success'
                    });
                    this.imageUrlF = URL.createObjectURL(file.raw)
                    this.form.picture = res.data
                    //将数据存入图片列表，方便后面删除
                    this.imagesUrl.push(res.data)
                    // console.log(this.imagesUrl)
                } else {
                    this.$message({
                        message: res.message,
                        type: 'warning'
                    });
                }
                // console.log(res)
            },
            //上传图片前的处理
            beforeAvatarUpload(file) {
                if (file.size > 10000000) {
                    this.$message({
                        message: '文件需小于等于10M',
                        type: 'warning'
                    });
                }
                // console.log(file,file.size)
            },
            //关闭表单框
            formClose() {
                this.imageUrl = ''
                this.imageUrlF = ''
                // this.imagesUrl.pop()
                // console.log(this.imagesUrl)
                this.imagesUrl.forEach(item => {
                    axios.get("/file/delmanpower?fileName=" + item).then((res) => {
                        if (res.flag) {
                            console.log("文件：【" + item + "】删除成功")
                        }
                    });
                })
                this.imagesUrl = []
            },
            //表单保存
            handleSave() {
                var method = ''
                var obj = {}
                //将最后一个图片文件保存，其余的删除
                this.imagesUrl.pop()
                this.imagesUrl.forEach(item => {
                    axios.get("/file/delmanpower?fileName=" + item).then((res) => {
                        if (res.flag) {
                            console.log("文件：【" + item + "】删除成功")
                        }
                    });
                })
                this.imageUrlF = ''
                this.imageUrl = ''
                this.imagesUrl = []
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
                        axios.post("/manpower/" + method, obj).then((res) => {
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
                    } else {
                        return false;
                    }
                })
                // this.$router.go(0)
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
                        //删除图片
                        var pic
                        axios.get("/manpower/selbyid?id=" + id).then((res) => {
                            pic=this.url+ res.data.picture
                        })
                        axios.get("/file/delmanpower?fileName=" + pic).then((res) => {
                            if (res.flag) {
                                console.log("文件：【" + pic + "】删除成功")
                            }
                        });
                    })
                    axios.post("/manpower/del", this.delIds).then((res) => {
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
                axios.post("/manpower/del", this.delIds).then((res) => {
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
                //删除图片
                var pic
                axios.get("/manpower/selbyid?id=" + id).then((res) => {
                    pic=this.url+ res.data.picture
                })
                axios.get("/file/delmanpower?fileName=" + pic).then((res) => {
                    if (res.flag) {
                        console.log("文件：【" + pic + "】删除成功")
                    }
                });
                this.reload()
                this.dialogVisible = false;
                this.delIds = []
                this.delId = ''
            },
            //选择器的数据
            searchOptions() {
                axios.get("/departmenttype/selall").then((res) => {
                    // console.log(this.searchMap)
                    if (res == null || res.length == 0) this.loading = true
                    else {
                        this.loading = false
                        this.options = res.data
                        // console.log(res)
                    }
                })
            },
            //选择器被选择，根据id查找，完成form数据完整
            selected() {
                axios.get("/departmenttype/selbyid?id=" + this.form.departmentId).then((res) => {
                    this.form.departmentName = res.data.name
                });
            },

            //时间区间发生改变，searcHMap中时间区间条件也要变化
            dateChange() {
                //没有选择时间区间或者清理时间条件，当前时间区间为空字符串
                const daterange = JSON.parse(JSON.stringify(this.selectDate))
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

            //预览图片
            imagePreview(e) {
                this.srcList.push(this.url + e.picture)
                // console.log(this.srcList)
            },
            //关闭预览图片处理
            imageClose() {
                this.srcList = []
            },
            //是否查看身份证号
            look(index) {
                if (this.List[index].idcLook == true) {
                    this.List[index].idcLook = false
                } else {
                    this.List[index].idcLook = true
                }
            }

            

        }
    }
</script>

<style scoped src="@/assets/css/content.css">


</style>
