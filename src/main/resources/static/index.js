const Counter= {
    data(){
        return{
            query:"",
            tbodyContext:"",
            hotId:""
        }
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
        },
        findHotInfoHistoryList(){
            console.log("执行历史信息查询......");
            $.ajax({
                url:"/hotSearch/findHotInfoHistoryList",
                data:{
                    "query" : this.query,
                    "id": this.hotId
                },
                type:"GET",
                async:false,
                dataType:"json",
                success:function(data){
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
                },
                error:function(data){
                    alert("执行失败！");
                }
            });
        }
    }
};
Vue.createApp(Counter).mount('#hotInfo');
