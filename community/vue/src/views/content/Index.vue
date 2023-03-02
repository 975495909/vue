<template>
    <div class="index" :style="user==null?'width: 95%;margin: 2%;':''">
        <el-row :gutter="20" type="flex" justify="space-around">
            <el-col :span="7" style="height: 300px;">
                <el-card shadow="hover" class="greet" :style="now>=8&&now<=17?'background-color: #ebffff;':'background-color: #e9ebff;'">
                    <h1>{{user==null?'游客':User.name}}</h1>
                    <h2>{{now>=8&&now<=11?'上午':now>=13&&now<=17?'下午':now>17&&now<=23?'晚上':now==12?'中午':'晚上'}}好</h2>
                    <el-image class="timeImg" :src="timeImg()"></el-image>
                </el-card>
            </el-col>
            <el-col :span="17" >
                <el-card shadow="hover" class="notice" v-show="user!=null">
                    <el-tabs type="border-card" style="height: 260px;overflow-y: auto;">
                        <el-tab-pane :label="item.name" v-for="item in notice">
                            <div v-for="item in item.content"
                                style="margin-top: 15px;border-bottom: 1px solid #e6e6e5;">
                                <el-row :gutter="20" type="flex" justify="space-between">
                                    <el-col :span="4">
                                        <h4>{{GuolvText(item.title)}}</h4>
                                    </el-col>
                                    <el-col :span="16">{{GuolvText1(item.description)}}</el-col>
                                    <el-col :span="4">
                                        <h4>{{item.tips}}</h4>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-tab-pane>
                    </el-tabs>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" type="flex" justify="space-around" style="margin-top: 8px;">
            <el-col :span="17">
                <el-card shadow="hover" class="carousel">
                    <el-carousel height="246px">
                        <el-carousel-item v-for="item in 3" :key="item">
                            <el-row :gutter="20" type="flex" justify="space-around" v-show="item==1"
                                style="height: 250px;">
                                <el-col :span="8" style="background-color: #fff6f3;">
                                    <div>
                                        <h3>人员统计：</h3>
                                        <h6>{{ownersTotal}}</h6>
                                        <h5>今日+{{owneradd}}</h5>
                                    </div>
                                </el-col>
                                <el-col :span="8" style="background-color: #f4ece9;">
                                    <div>
                                        <h3>车辆统计：</h3>
                                        <h6>{{carsTotal}}</h6>
                                        <h5>今日+{{caradd}}</h5>
                                    </div>
                                </el-col>
                                <el-col :span="8" style="background-color: #eae4e2;">
                                    <div>
                                        <h3>宠物统计：</h3>
                                        <h6>{{petsTotal}}</h6>
                                        <h5>今日+{{petadd}}</h5>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="20" type="flex" justify="space-around" v-show="item==2"
                                style="height: 250px;">
                                <el-col :span="12" style="background-color: #fff0fd;">
                                    <div>
                                        <h3>楼栋统计：</h3>
                                        <h6>{{buildingsTotal}}</h6>
                                        <h5>今日+{{buildingadd}}</h5>
                                    </div>
                                </el-col>
                                <el-col :span="12" style="background-color: #ffeeee;">
                                    <div>
                                        <h3>房屋统计：</h3>
                                        <h6>{{housesTotal}}</h6>
                                        <h5>今日+{{houseadd}}</h5>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="20" type="flex" justify="space-around" v-show="item==3"
                                style="height: 250px;">
                                <el-col :span="12" style="background-color: #e9fff8;">
                                    <div>
                                        <h3>维护统计(已解决)：</h3>
                                        <h6>{{repairsTotal}}</h6>
                                        <h5>今日+{{repairadd}}</h5>
                                    </div>
                                </el-col>
                                <el-col :span="12" style="background-color: #e9f6ff;">
                                    <div>
                                        <h3>投诉统计(已解决)：</h3>
                                        <h6>{{complaintsTotal}}</h6>
                                        <h5>今日+{{complaintadd}}</h5>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-carousel-item>
                    </el-carousel>
                </el-card>
            </el-col>
            <el-col :span="7" style="height: 290px;" >
                <div class="chart" v-show="user!=null">
                    <div id="myChart" style="width: 100%;height: 100%;"></div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import axios from '@/utils/request'
    import echarts from 'echarts'
    import '@/utils/purple-passion.js'
    export default {
        name: "index",
        data() {
            return {
                user: sessionStorage.getItem('user'),
                User: {
                    "username": sessionStorage.getItem('user')
                },
                now: new Date().getHours(),
                notice: [{
                    name: '活动信息',
                    content: []

                }, {
                    name: '报修信息',
                    content: []

                }, {
                    name: '投诉信息',
                    content: []

                }, {
                    name: '资产信息',
                    content: []

                }, ],
                //统计的数据
                getData: [{
                    name: '已出租',
                    value: 0
                }, {
                    name: '已有住户',
                    value: 0
                }, {
                    name: '无住户',
                    value: 0
                }],
                //总数
                ownersTotal: 0,
                carsTotal: 0,
                petsTotal: 0,
                buildingsTotal: 0,
                housesTotal: 0,
                repairsTotal: 0,
                complaintsTotal: 0,
                //今日新增
                owneradd: 0,
                caradd: 0,
                petadd: 0,
                buildingadd: 0,
                houseadd: 0,
                repairadd: 0,
                complaintadd: 0,

                // timeImg: require('@/assets/太阳.png')

            }

        },
        setup() {
            return {

            }
        },
        created() {

            //展示个人信息
            axios.post("/manager/selpic", this.User).then((res) => {
                this.User = res.data
            })

            this.selall('activity')
            this.selall('repair')
            this.selall('complaint')
            this.selall('resources')

            this.Total('owner')
            this.Total('car')
            this.Total('pet')
            this.Total('building')
            this.Total('house')
            this.Total('repair')
            this.Total('complaint')

        },
        mounted() {
            //统计图
            this.initData()
        },
        methods: {
            selall(name) {
                axios.get("/" + name + "/selall").then((res) => {
                    // console.log(res.data)
                    var obj
                    res.data.forEach(item => {
                        // console.log(item.status)
                        obj = {}
                        if (name == 'activity' && item.status == '2') {
                            obj.title = item.title
                            obj.description = item.description
                            obj.tips = '正在进行中'
                            this.notice[0].content.push(obj)
                        }
                        if (name == 'repair' && item.status == '0') {
                            obj.title = item.buildingName
                            obj.description = item.description
                            obj.tips = '待受理'
                            this.notice[1].content.push(obj)
                        }
                        if (name == 'complaint' && item.status == '0') {
                            obj.title = item.buildingName
                            obj.description = item.description
                            obj.tips = '待受理'
                            this.notice[2].content.push(obj)
                        }
                        if (name == 'resources' && item.status == '1') {
                            obj.title = item.name
                            obj.tips = '已过期'
                            this.notice[3].content.push(obj)
                        }
                    })
                })
            },
            //过滤
            GuolvText(value) {
                if (!value) return ''
                if (value.length > 6) {
                    return value.slice(1, 6) + '...'
                }
                return value
            },
            GuolvText1(value) {
                if (!value) return ''
                if (value.length > 27) {
                    return value.slice(1, 27) + '...'
                }
                return value
            },
            //时间图片
            timeImg() {

                if (this.now >= 8 && this.now <= 11) return require('@/assets/太阳升起.png')
                else if (this.now >= 13 && this.now <= 17) return require('@/assets/太阳下山.png')
                else if (this.now == 12) return require('@/assets/太阳.png')
                else return require('@/assets/月亮.png')
            },

            //初始化饼状图数据
            initData() {

                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById("myChart"), 'purple-passion')
                axios.get("/house/selall").then((res) => {
                    res.data.forEach(item => {
                        // console.log(item.type)
                        if (item.status == 2) {
                            this.getData[0].value++
                        } else if (item.status == 1) {
                            this.getData[1].value++
                        } else this.getData[2].value++
                    })
                    var getData = JSON.parse(JSON.stringify(this.getData))
                    // 绘制图表
                    myChart.setOption({
                        title: {
                            text: '住房情况',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: "item",
                            formatter: "{a} <br/>{b} : {c} ({d}%)",
                        },
                        legend: {
                            orient: 'vertical',
                            bottom: 'bottom',
                            data: getData
                        },
                        series: [{
                            name: "选项内容",
                            type: "pie",
                            radius: "55%",
                            center: ["50%", "50%"], //位置
                            data: getData,
                        }, ],
                    })
                })

            },
            //年月日
            initDate(time) {
                var t = new Date(time)
                time = t.getFullYear() + '-' + (t.getMonth() + 1) + '-' + t.getDate()
                // console.log(time)
                return new Date(time).getTime()
            },
            //计算总数
            Total(name) {
                axios.get("/" + name + "/selall").then((res) => {
                    // console.log(res.data.length)
                    if (name == "owner") {
                        this.ownersTotal = res.data.length
                        res.data.forEach(item => {
                            if (this.initDate(item.createTime) === this.initDate(Date()))
                                this.owneradd++
                        })
                    }
                    if (name == "car") {
                        this.carsTotal = res.data.length
                        res.data.forEach(item => {
                            if (this.initDate(item.createTime) === this.initDate(Date()))
                                this.caradd++
                        })
                    }
                    if (name == "pet") {
                        this.petsTotal = res.data.length
                        res.data.forEach(item => {
                            if (this.initDate(item.createTime) === this.initDate(Date()))
                                this.petadd++
                        })
                    }
                    if (name == "building") {
                        this.buildingsTotal = res.data.length
                        res.data.forEach(item => {
                            if (this.initDate(item.createTime) === this.initDate(Date()))
                                this.buildingadd++
                        })
                    }
                    if (name == "house") {
                        this.housesTotal = res.data.length
                        res.data.forEach(item => {
                            if (this.initDate(item.createTime) === this.initDate(Date()))
                                this.houseadd++
                        })
                    }
                    if (name == "repair") {
                        res.data.forEach(item => {
                            if (item.status == 1) {
                                this.repairsTotal++
                                if (this.initDate(item.createTime) === this.initDate(Date()))
                                    this.repairadd++
                            }
                        })
                        // this.repairsTotal=res.data.length
                    }
                    if (name == "complaint") {
                        res.data.forEach(item => {
                            if (item.status == 1) {
                                this.complaintsTotal++
                                if (this.initDate(item.createTime) === this.initDate(Date()))
                                    this.complaintadd++
                            }
                        })
                        // this.complaintsTotal=res.data.length
                    }
                })
            }

        },
    }
</script>
<style scoped>
    .greet {
        width: 100%;
        height: 100%;
        /* background-color: aqua; */
    }

    .notice {
        width: 100%;
        height: 300px;
        /* background-color: aqua; */

    }

    .chart {
        width: 100%;
        height: 100%;

    }

    .carousel {
        width: 100%;
        height: 100%;
    }

    h1 {
        margin-top: 50px;
        font-size: 3ch;
        color: #4e4c55;
    }

    h2 {
        margin-top: 40px;
        font-size: 5ch;
        color: #4e4c55;
    }

    h3 {
        margin-top: 8%;
        margin-left: 2%;
    }

    h5 {
        margin-left: 80%;
        margin-top: 5%;
        color: #55ac8e;
    }

    h6 {
        margin-top: 40px;
        font-size: 5ch;
        margin-left: 25%;
        margin-top: 15%;
    }

    .timeImg {
        width: 150px;
        height: 150px;
        position: absolute;
        top: 15%;
        right: 75%;
    }
</style>
