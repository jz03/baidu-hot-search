
const Counter= {
    data(){
        return{
            query:"",
            hotId:"",
            option:{
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [150, 230, 224, 218, 135, 147, 260],
                        type: 'line',
                        smooth: true
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
            httpReq(this.query,this.hotId);
            this.option.series[0].smooth=false;
            myChart.setOption(this.option);
        }
}
};
Vue.createApp(Counter).mount('#hotInfo');

function httpReq(query,hotId){
    console.log("httpReq***********");
    $.ajax({
        url:"/hotSearch/findHotInfoHistoryList",
        data:{
            "query" : query,
            "id": hotId
        },
        type:"GET",
        async:false,
        dataType:"json",
        success:function(data){
            fillTableBodyContext(data);
        },
        error:function(data){
            alert("执行失败！");
        }
    });
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

