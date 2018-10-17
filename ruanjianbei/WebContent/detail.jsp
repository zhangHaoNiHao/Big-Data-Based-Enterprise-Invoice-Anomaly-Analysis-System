<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业异常信息分析系统</title>
<link href="${pageContext.request.contextPath}/css/style.css" type='text/css' rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<style>
.div-inline{ display:inline} 
</style>
</head>

<body>
	<div class='header'>
        <p>企业异常预测</p>
    </div>
    <div class="content">
        <div class="nav">
            <ul>
                <li class="current"><a href="${pageContext.request.contextPath}/index.jsp">总览</a></li>
                <li><a href="${pageContext.request.contextPath}/select.jsp">企业查询</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="title">企业关系图</div>
            <div class="show">
                <div class="div-inline" id="main" style="width: 500px;height:400px;"></div>
                <div class="div-inline" id="main5" style="width: 500px;height:400px;" ></div>
                
            </div>
             
        </div>
    </div>
    
	<script>
		//基于准备好的dom，初始化echarts实例
		/*
		var myChart = echarts.init(document.getElementById('main'));
		//指定图表的配置项和数据
		option = {
		    
		   	 title: {
		   	        text: ''
		   	    },
		   	    tooltip: {},
		   	    animationDurationUpdate: 1500,
		   	    animationEasingUpdate: 'quinticInOut',
		   	    label: {
		   	        normal: {
		   	            show: true,
		   	            textStyle: {
		   	                fontSize: 12
		   	            },
		   	        }
		   	    },
		   	    legend: {
		   	        x: "center",
		   	        show: false,
		   	        data: ["卖家", "买家"]
		   	    },
		   	    series: [
		
		   	        {
		   	            type: 'graph',
		   	            layout: 'force',
		   	            symbolSize: 45,
		   	            focusNodeAdjacency: true,
		   	            roam: true,
		   	            categories: [{
		   	                name: '卖家',
		   	                itemStyle: {
		   	                    normal: {
		   	                        color: "#009800",
		   	                    }
		   	                }
		   	            }, {
		   	                name: '买家',
		   	                itemStyle: {
		   	                    normal: {
		   	                        color: "#4592FF",
		   	                    }
		   	                }
		   	            }],
		   	            label: {
		   	                normal: {
		   	                    show: true,
		   	                    textStyle: {
		   	                        fontSize: 12
		   	                    },
		   	                }
		   	            },
		   	            force: {
		   	                repulsion: 1000
		   	            },
		   	            edgeSymbolSize: [4, 50],
		   	            edgeLabel: {
		   	                normal: {
		   	                    show: true,
		   	                    textStyle: {
		   	                        fontSize: 10
		   	                    },
		   	                    formatter: "{c}"
		   	                }
		   	            },
		   	            data:  [ ],
		   	            lineStyle: {
		   	                normal: {
		   	                    opacity: 0.9,
		   	                    width: 1,
		   	                    curveness: 0
		   	                }
		   	            }
		   	        }
		   	    ]
				
		};
		myChart.setOption(option);
		myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
		
		function GetQueryString(name) {
		
			   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
		
			   var r = window.location.search.substr(1).match(reg);
		
			   if (r!=null) return (r[2]); return null;
		
		};
			  var nsr_id=GetQueryString("id");
		      alert(nsr_id)
			  var option2 = myChart.getOption();
		      var datas=[];
		      // name: '赵成',
		      category: 1,
		      //draggable: true, 
		      $.post(
						"${pageContext.request.contextPath}/Servlet",
						{
							method : "detail",
							id: nsr_id
						},
						function(result) {
							if (result) {
				                 for(var i=0;i<result.length;i++){
				                	 datas[i] = {
				                	            name: result[i][0],
				                	            category: 1,
				                                draggable: true,
				                	        };//挨个取出类别并填入类别数组 
				                   
				                  } 
				                 //alert(datas);
				                 //alert(option2.series[0].data);
				                 var json=JSON.stringify(datas);
				                 
				                 option2.series[0].data=JSON.parse(json);
				                 alert(option2.series[0].data);
				              //    json=JSON.stringify(datas);
				                
				                 //json=JSON.parse(datas);
				                 myChart.setOption(option2);
				                 myChart.hideLoading();    //隐藏加载动画

				          }
						}, "json");
		     
		      */
		      //////////////第二个图
		      function GetQueryString(name) {
		
				   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
			
				   var r = window.location.search.substr(1).match(reg);
			
				   if (r!=null) return (r[2]); return null;
		
			  };
			  var nsr_id=GetQueryString("id");
		      
			  /*
		      var myChart = echarts.init(document.getElementById('main5'));
		   	  var option2 = {
		   			    title: {
		   			        text: '企业每月购销金额',
		   			        //subtext: '纯属虚构'
		   			    },
		   			    tooltip: {
		   			        trigger: 'axis'
		   			    },
		   			    legend: {
		   			        data:['销售金额','购项金额']
		   			    },
		   			    toolbox: {
		   			        show: true,
		   			        feature: {
		   			            dataZoom: {
		   			                yAxisIndex: 'none'
		   			            },
		   			            dataView: {readOnly: false},
		   			            magicType: {type: ['line', 'bar']},
		   			            restore: {},
		   			            saveAsImage: {}
		   			        }
		   			    },
		   			    xAxis:  {
		   			        type: 'category',
		   			        boundaryGap: false, 
		   			        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'] //'周一','周二','周三','周四','周五','周六','周日'
		   			    },
		   			    yAxis: {
		   			        type: 'value',
		   			        axisLabel: {
		   			            formatter: '{value} ￥'
		   			        }
		   			    },
		   			    series: [
		   			        {
		   			            name:'销售金额',
		   			            type:'line',
		   			            data:[11, 11, 15, 13, 12, 13, 10],//11, 11, 15, 13, 12, 13, 10
		   			            markPoint: {
		   			                data: [
		   			                    {type: 'max', name: '最大值'},
		   			                    {type: 'min', name: '最小值'}
		   			                ]
		   			            },
		   			            markLine: {
		   			                data: [
		   			                    {type: 'average', name: '平均值'}
		   			                ]
		   			            }
		   			        },
		   			        {
		   			            name:'购项金额',
		   			            type:'line',
		   			            data:[1, -2, 2, 5, 3, 2, 0], //1, -2, 2, 5, 3, 2, 0
		   			            markPoint: {
		   			                data: [
		   			                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
		   			                ]
		   			            },
		   			            markLine: {
		   			                data: [
		   			                    {type: 'average', name: '平均值'},
		   			                    [{
		   			                        symbol: 'none',
		   			                        x: '90%',
		   			                        yAxis: 'max'
		   			                    }, {
		   			                        symbol: 'circle',
		   			                        label: {
		   			                            normal: {
		   			                                position: 'start',
		   			                                formatter: '最大值'
		   			                            }
		   			                        },
		   			                        type: 'max',
		   			                        name: '最高点'
		   			                    }]
		   			                ]
		   			            }
		   			        }
		   			    ]
		   			};
		   			myChart.setOption(option2);
		   			*/
		   			var myChart = echarts.init(document.getElementById('main5'));
		   		    var option = {
		   			    xAxis: {
		   			        type: 'category',
		   			        data: []  // 'Mon','Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'
		   			    },
		   			    yAxis: {
		   			        type: 'value'
		   			    },
		   			    series: [{    //820, 932, 901, 934, 1290, 1330, 1320
		   			        data: [],
		   			        type: 'line'
		   			    }]
		   			};
		   			$(function(){
		   	   			
		   				alert(nsr_id)
		   				$.post(
		   						"${pageContext.request.contextPath}/Servlet",
		   						{
		   							method : "x_yf_jshj",
		   							hydm: nsr_id
		   						},
		   						
		   						function(data) {
		   							if (data != null && data.length > 0) {
		   								
		   								$.each(data, function (index, value) {  
		   									option.xAxis.data.push({value:data[index].kpyf });
		   									option.series[0].data.push({value:data[index].jshj });
		   	       						 })
		   	       						 
		   								myChart.setOption(option);
		   							}
		   						}, "json");
		   			});
   
</script>
</body>
</html>