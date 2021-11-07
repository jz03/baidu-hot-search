const Counter= {
    data(){
        return{
            query:"",
            tbodyContext:"<tr><td>369</td><td>新华社揭批疫情震惊体网文</td><td>近日，新华社揭批 ：“标题党”毫无疑问就是这种背景下的产物——语不惊人死不休的标题后面，是一整套精心设计的话术，包裹在其中的是唯利是图的心。</td><td>null</td></tr><tr><td>364</td><td>黑龙江河南四川等地疫情仍在发展中</td><td>11月6日，国家卫健委疾控局副局长吴良有介绍，黑龙江、河北、河南、江西、四川、重庆、辽宁大连等地疫情仍在发展中，需密切关注疫情走向。</td><td>null</td></tr><tr><td>349</td><td>本轮疫情由多个境外输入源头引起</td><td>11月6日，国家卫生健康委疾控局副局长吴良有表示，病毒基因测序和流调溯源结果显示，本轮疫情由多个不关联的境外输入源头引起。</td><td>null</td></tr><tr><td>331</td><td>钟南山谈冬季疫情防控2个不利因素</td><td>11月5日，钟南山院士在接受记者采访时表示：现在有两个不利因素，一是这次在西北散发比较多，增加了追踪难度；二是冬季在北方人们聚集得比较多。</td><td>null</td></tr><tr><td>316</td><td>成都涉疫情企业负责人出镜道歉</td><td>11月5日，成都涉疫情相关企业负责人发布致歉视频：之前防控意识淡薄，十分愧疚，对所有医护人员表示深深的歉意。</td><td>null</td></tr><tr><td>291</td><td>成都本轮疫情首例确诊被立案侦查</td><td>经调查，李某（男，23岁）从外地返蓉后，拒绝执行疫情防控措施，隐瞒中高风险地区旅居史，涉嫌妨害传染病防治罪，成都市金牛区公安分局已依法立案侦查。</td><td>null</td></tr><tr><td>284</td><td>疫情下的亚洲最大社区天通苑</td><td>11月2日，北京天通苑北二区被定为中风险地区。北京每21个人可能就有1个生活在天通苑，作为北京人口最密集的区域之一，天通苑又成了北京人关心的地方。</td><td>null</td></tr><tr><td>237</td><td>郑州一度假区或为疫情传播关键点</td><td>10月末以来，江西、河南相继出现本土疫情。梳理官方公布的病例轨迹，两省三地的感染链条指向一个关键地点——郑州新密市银基冰雪世界度假区。</td><td>null</td></tr><tr><td>220</td><td>河北疫情存多条传播链 涉婚宴等</td><td>11月4日上午，河北省石家庄市疫情防控新闻发布会上通报，河北本轮疫情存多条传播链：涉婚宴、幼儿园、苹果摊等。</td><td>null</td></tr><tr><td>190</td><td>本轮疫情497个轨迹相关病例关系图</td><td>自10月17日西安报告2例阳性病例以来，全国累计报告新增本土阳性病例已超700例，其中497例阳性感染者的轨迹或基因测序与内蒙古、甘肃等地疫情有关联。</td><td>null</td></tr><tr><td>151</td><td>1个苹果摊与2起家庭聚集性疫情</td><td>买卖苹果疑引发新感染，河北此轮疫情已涉及医院、小学、幼儿园、婚宴。其中，一个苹果摊，或成为了关联深泽、辛集两个家庭及其相关病例的关键。</td><td>null</td></tr><tr><td>149</td><td>日本疫情消退是因病毒自我消亡?</td><td>近日，有媒体表示，日本疫情趋缓，与新冠病毒存在“自我消亡”有关。真的是这样吗？</td><td>null</td></tr><tr><td>139</td><td>深泽县副县长因疫情处置不力被停职</td><td>11月3日，石家庄市召开疫情防控新闻发布会。会上通报，深泽县副县长贾光因对疫情处置不力被停职，卫健局局长贾卫东等人在疫情防控中履职不力被免职。</td><td>null</td></tr><tr><td>99</td><td>张文宏:疫情还在高危运行期</td><td>张文宏医生发文：疫情还在高危运行期。上海的这场疫情防控也刚刚开始，远不能说已经取得胜利。相信我们的国家与人民一定会远离风险。</td><td>null</td></tr><tr><td>56</td><td>16省538例 百人规模疫情为何频发</td><td>从10月17日开始的本土疫情，16天波及16省，累积确诊538人。自5月以来，我国本土经历了7轮“确诊人数百人以上”疫情。百人以上规模疫情为何频频发生？</td><td>null</td></tr><tr><td>3</td><td>疫情15天波及16省份 哪里大意了?</td><td>自10月17日西安2例本土确诊以来，已有12省（市、区）报告与内蒙古关联感染病例，另有4省6地出现独立传播链疫情，暴露出防控工作中诸多问题。</td><td>null</td></tr>",
            hotInfoList:""
        }
    },
    methods: {
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
                url:"/hotSearch/findHotInfoList0001",
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
                        + "<td>" +data[i].id+ "</td>"
                        + "<td>" +data[i].query+ "</td>"
                        + "<td>" +data[i].desc+ "</td>"
                        + "<td>" +data[i].createDate+ "</td>"
                        + "</tr>";
                        // console.log(date);
                    }
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
