/**
 * 
 */
/*这是从国家统计局网站下载的全国行政区划代码，数据更新至2015-9-30，我把他转化为JSON对象，然后定义了一个place类，通过遍历这个JSON把所有地区都转化为place对象。然后通过判断地区编码的特征，把所有place对象归类到三个数组里面，分别代表省级地区、市级地区、县级地区*/  
    var placesMap={"11000":"数学","11011":"数学史","11017":"数论","12000":"信息科学与系统科学","12010":"信息科学与系统科学基础学科"}
    /*地区类*/  
    function place(Code,Name){  
        this.Code=Code;//地区编码  
        this.Name=Name;//地名  
        /*地区类的PlaceType方法，返回值：String类型，表示地区类别，"p"代表省/直辖市、特别行政区，"c"代表市，"d"代表区/县。*/  
        place.prototype.Type=function(){  
            var ac=parseInt(this.Code);  
            if(ac%100!=0){  
                return "b";  
            }else{  
                return "a";   
            }  
        }  
        /*返回地点所属省编码*/  
        place.prototype.BCode=function(){  
            //整除10000得到省级编码（前2位数字）  
            var ac=parseInt(this.Code);  
            return ac;   
        }  
        /*返回地点所属市编码*/  
        place.prototype.ACode=function(){  
            //整除100得到市级编码（前4位数字）  
            var ac=parseInt(this.Code);  
            return Math.floor(ac/100);  
        }  
    }  
      
    var A=new Array();//省数组  
    var B=new Array();//市数组   
    /*initSeletList()这个函数初始化上面这三个数组，还有省下拉列表*/  
    function initSeletList(){  
        //遍历placesMap这个Json对象,根据key：value对创建place对象，并根据地区类型分类  
        for (var key in placesMap){  
            var pl=new place(key,placesMap[key]);  
            var ty=pl.Type();   
            if(ty=="a"){  
                A.push(pl);  
            }  
            if(ty=="b"){  
                B.push(pl);  
            }  
        }  
        //初始化省下拉选择列表  
        for(var i=0;i<A.length;i++){  
            var op=document.createElement("option");  
            op.text=A[i].Name;  
            op.value=A[i].ACode();  
            document.getElementById("A").appendChild(op);  
        }     
    }  
    /*下拉列表选项改变时执行此函数*/  
    function changes(element){  
        var id=element.getAttribute("id");  
        /*省下拉列表改变时更新市下拉列表和区下拉列表*/  
        if(id=="A"&&element.value!="000000"){  
            document.getElementById("B").options.length=1;//清除市下拉列表旧选项  
            var pCode=parseInt(element.value);//获取省下拉列表被选取项的省编码  
            /*根据省编码更新市下拉列表*/  
            if(pCode!=0){  
                for(var i=0;i<B.length;i++){  
                    if(B[i].ACode()==pCode){  
                        var op=document.createElement("option");  
                        op.text=B[i].Name;  
                        op.value=B[i].BCode();  
                        document.getElementById("B").appendChild(op);  
                    }  
                }  
                alert(element.text());
            }  
           
        }  
       
    }  
    window.onload=function(){  
        initSeletList();  
    }; 