/**
 * 
 */
/*这是从国家统计局网站下载的全国行政区划代码，数据更新至2015-9-30，我把他转化为JSON对象，然后定义了一个place类，通过遍历这个JSON把所有地区都转化为place对象。然后通过判断地区编码的特征，把所有place对象归类到三个数组里面，分别代表省级地区、市级地区、县级地区*/  
    var placesMap={"11000":"数学","11011":"数学史","11017":"数论","12000":"信息科学与系统科学","12010":"信息科学与系统科学基础学科","13000":"dsa"}
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
      
    
    /*initSeletList()这个函数初始化上面这三个数组，还有省下拉列表*/  
    function Showshu(){  
    	var A=new Array();//省数组  
        var B=new Array();//市数组   
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
       
        var s="";
        for(var i=0;i<A.length;i++){ 
        	s+="<li>";
        	//var li1=document.createElement("li");  
           /* li1.innerHTML = A[i].Name;
            var ul2=document.createElement("ul");*/
        	s+="<a target='mainAction' href='../XuqiuServlet?method=shu&code="+A[i].ACode()+"'>"+A[i].Name+"</a>";
        	s+="<ul>";
            for(var j=0;j<B.length;j++){  
                if(B[j].ACode()==A[i].ACode()){  
                	 /*var li2=document.createElement("li");  
                	 li2.innerHTML = B[i].Name;
                     ul2.appendChild(li2);*/
                	s+="<li> <a target='mainAction' href='../XuqiuServlet?method=shu&code="+B[j].Code+"'>"+B[j].Name+"</a> </li>";
                }  
            } 
           // li.appendChild(ul);
            //li1.appendChild(ul2);
           
            s+="</ul>";
            s+="</li>";
           
            //li1.innerHTML = s;
            //document.getElementById("shu").appendChild(li1);
        } 
        
        //alert(s);
     /*   var ss=new Array();
        ss[0]="<";
        ss[1]="2";*/
        window.parent.frames["frame1"].cols="10%,*";
       // document.getElementById("tree").innerHTML=s;
        window.parent.frames["mainAction"].location.href="../xuqiu/list.jsp";
       
    }  
    
    function Closeshu(){  
        window.parent.frames["frame1"].cols="0,*";
    }
    
    window.onload=function(){  
    	Showshu(); 
    }
    