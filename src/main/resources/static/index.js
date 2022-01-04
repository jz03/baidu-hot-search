const Counter= {
    data(){
        return{
            query:"",
            tbodyContext:"",
            hotId:"",
            option:{
                title:{
                    text:"每日信息数目"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['信息数目']
                },
                xAxis: {
                    type: 'category',
                    data: []
                },
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '数目',
                        yAxisIndex:0,
                        data: [],
                        type: 'line',
                        smooth: true
                    }
                ]
            }
        }
    },
    mounted(){
        var res = httpReq();
        let myChart = echarts.init(document.getElementById('echartsMain'));
        //x轴的数据
        this.option.xAxis.data = res.xData;
        //y轴的数据
        this.option.series[0].data =res.yData;
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
        //热点信息查询
        findHotInfoList(){
            $.ajax({
                url:"/hotSearch/findHotInfoList",
                data:{
                    "query" : this.query
                },
                type:"GET",
                async:false,
                dataType:"json",
                success:function(data){
                    let html = "";
                    for (let i = 0; i < data.length; i++) {
                        html = html + "<tr>"
                            + "<td>" + data[i].id + "</td>"
                            + "<td>" + "<a href='" + data[i].url + "'>" + data[i].query + "</a>" + "</td>"
                            + "<td>" + data[i].desc + "</td>"
                            + "<td>" + data[i].createDate + "</td>"
                            + "</tr>";
                        // console.log(date);
                    }
                    //使用jq中的技术进行动态写入html字符串
                    let parhtml = $.parseHTML(html);
                    $( "#tbodyStringToHtml" ).empty();
                    $( "#tbodyStringToHtml" ).append(parhtml);
                },
                error:function(data){
                    alert("执行失败！");
                }
            });
        }
    }
};
Vue.createApp(Counter).mount('#hotInfo');

function httpReq(){
    var res = {};
    $.ajax({
        url:"/hotSearch/findHotCountDate",
        type:"GET",
        async:false,
        dataType:"json",
        success:function(resData){
            res = resData;
        },
        error:function(data){
            alert("执行失败！");
        }
    });
    return res;
}
