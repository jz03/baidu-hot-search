
const Counter= {
    data(){
        return{
            query:"",
            hotId:"",
            option:{
                title:{
                    text:""
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['热搜指数', '排序']
                },
                xAxis: {
                    type: 'category',
                    data: []
                },
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '热搜指数',
                        yAxisIndex:0,
                        data: [],
                        type: 'line',
                        smooth: true
                    },
                    {
                        name: '排序',
                        yAxisIndex:1,
                        data: [],
                        type: 'line',
                        smooth: false
                    }
                ]
            }

        }
    },
    mounted(){
        let myChart= echarts.init(document.getElementById('echartsMain'));
        myChart.setOption(this.option);
    },
    methods: {
        //抓取百度热搜的数据
        run(){
            console.log("执行..........");
            $.ajax({
                url:"/hotSearch/grabHotSearchData",
                data:{},
                type:"GET",
                success:function(data){
                    alert("执行成功！");
                },
                error:function(data){
                    alert("执行失败！");
                }
            });
        },

        findHotInfoHistoryList(){
            let myChart = echarts.init(document.getElementById('echartsMain'));
            console.log("执行历史信息查询......");
            var res = httpReq(this.query,this.hotId);
            this.option.title.text = res.data[0].query;
            //x轴的数据
            this.option.xAxis.data = res.xData;
            //y轴的数据
            this.option.series[0].data =res.yData;
            this.option.series[1].data =res.yIndexData;
            myChart.setOption(this.option);
        }
}
};
Vue.createApp(Counter).mount('#hotInfo');

function httpReq(query,hotId){
    console.log("httpReq***********");
    var res = {};
    $.ajax({
        url:"/hotSearch/findHotInfoHistoryList",
        data:{
            "query" : query,
            "id": hotId
        },
        type:"GET",
        async:false,
        dataType:"json",
        success:function(resData){
            fillTableBodyContext(resData.data);
            res = resData;
        },
        error:function(data){
            alert("执行失败！");
        }
    });
    return res;
}
//热搜历史填充
function fillTableBodyContext(data){
    let html = "";
    for (let i = 0; i < data.length; i++) {
        html = html + "<tr>"
            + "<td>" + data[i].branchId + "</td>"
            + "<td>" + data[i].index + "</td>"
            + "<td>" + data[i].hotScore + "</td>"
            + "<td>" + data[i].query + "</td>"
            + "<td>" + data[i].createDate + "</td>"
            + "</tr>";
        // console.log(date);
    }
    //使用jq中的技术进行动态写入html字符串
    let parhtml = $.parseHTML(html);
    $( "#tbodyStringToHtml" ).empty();
    $( "#tbodyStringToHtml" ).append(parhtml);
}

