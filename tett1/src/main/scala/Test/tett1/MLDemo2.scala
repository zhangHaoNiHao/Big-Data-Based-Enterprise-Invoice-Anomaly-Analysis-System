package Test.tett1

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegression

object MLDemo2 {
  
  def main(args: Array[String]): Unit = {
				val sess = SparkSession.builder().appName("ml").master("local[4]").getOrCreate();
				val sc = sess.sparkContext;
				//数据目录
				//val dataDir = "hdfs://weekend110:9000/data/xx_all_error"
				
				val dataDir = "hdfs://weekend110:9000/user/hive/warehouse/nsr2_xfp"
				//定义样例类
//				case class FP(fp_nid:String,nsr_id:String,gf_id:String,hydm:String,djzclx_dm:String,kydjrq:String,xgrq:String,
//				    je:String,se:String,jshj:String,kpyf:String,kprq:String,zfbz:String,date_key:String,
//				    hwmc:String,ggxh:String,dw:String,sl:String,dj:String,je1:String,se1:String,spbm:String,
//				    label:String)
				
      case class FP(fp_nid:String,nsr_id:String,gf_id:String,hydm:String,djzclx_dm:String,kydjrq:String,xgrq:String,
				    je:String,se:String,jshj:String,kpyf:String,kprq:String,zfbz:String,
				    label:String)

				//变换
				//0.fp_nid,1.nsr_id,2.gf_id,2.hydm,3.djzclx_dm,4.kydjrq,5.xgrq,6.je,7.se,8.jshj,9.kpyf,10.kprq,11.zfbz,12.date_key,13.hwmc,14.ggxh,15.dw,16.sl,17.dj,18.je je1,19.se1,20.spbm,21.label
//				val fpDataRDD = sc.textFile(dataDir).map(_.split("\001")).map(f => FP(f(0).toString, 
//				  f(1).toString,f(2).toString,f(3).toString,f(4).toString,f(5).toString,f(6).toString, 
//					f(7).toString, f(8).toString,f(9).toString,f(10).toString,f(11).toString,f(12).toString,
//					f(13).toString,f(14).toString,f(15).toString,f(16).toString,f(17).toString,f(18).toString,
//					f(19).toString,f(20).toString,f(21).toString,f(22).toString()))
				  val fpDataRDD = sc.textFile(dataDir).map(_.split("\001")).map(f => FP(f(0).toString, 
				  f(1).toString,f(2).toString,f(3).toString,f(4).toString,f(5).toString,f(6).toString, 
					f(7).toString, f(8).toString,f(9).toString,f(10).toString,f(11).toString,f(12).toString,
					f(13).toString))

				import sess.implicits._
				//f.label.replaceAll("[)]","")
				
				def strToDouble(str: String): Double = {
          val regex = """([0-9]+)""".r
          val res = str match{
            case regex(num) => num
            case _ => "1"
          }
          val resDouble = res.toDouble
          resDouble
        }
				
				//转换RDD成DataFrame
				//1.fp_nid 2.nsr_id 3.gf_id 4.zfbz 5.hydm 6.djzclx_dm 7.je 8.se 9.jshj 10.kpyf 11.date_key 12.sl 13.dj 14.je1 15.se1 16.spbm
				val trainingDF = fpDataRDD.map(f => (f.label.replaceAll("[)]","").toDouble,
				    Vectors.dense( 
				    //f.fp_nid.substring(6).toDouble,
				    //if(f.nsr_id.equals("\\N")) 0 else f.nsr_id.toDouble,
				    //f.nsr_id.toDouble,
				    //if(f.gf_id.equals("\\N")) 0 else f.gf_id.toDouble,
				    //f.gf_id.toDouble,
				    
				    if(f.zfbz.equals("N)")) 1 else 0,
				    f.hydm.replaceAll("[(]","").toDouble,
				    f.djzclx_dm.toDouble,
				    //if(f.kpyf.equals("\\N")) -1 else f.kpyf.toDouble,
				    f.kpyf.toDouble,
				    //f.date_key.toDouble,
				    
				    strToDouble(f.je),
            strToDouble(f.se),
            strToDouble(f.jshj)
            
            //strToDouble(f.sl),
              //if(f.sl.equals("\\N")) 1 else f.sl.toDouble,
            //strToDouble(f.dj),
              //if(f.dj.equals("\\N")) 0 else f.dj.toDouble,
            //strToDouble(f.je1),
              //if(f.je1.equals("\\N")) 0 else f.je1.toDouble,           
            //strToDouble(f.se1),
              //if(f.se1.equals("\\N")) 0 else f.se1.toDouble
            //f.se1.toDouble
            //strToDouble(f.spbm)
            //if(f.spbm.equals("\\N")) 0 else f.spbm.replaceAll("[)]","").toDouble
            ))).toDF("label", "features")	
						
				//显式数据
				trainingDF.show()
				println("======================")

				//创建线性回归对象
				val lr = new LinearRegression()
				//设置最大迭代次数
				lr.setMaxIter(50)
				//通过线性回归拟合训练数据，生成模型
				val model = lr.fit(trainingDF)

				//创建内存测试数据数据框
//				val testDF = sess.createDataFrame(Seq(
//				    (1,Vectors.dense(42843.59,7283.41,50127.0,16.93,3470.08547008547,58748.55,9987.25)),
//				    (1,Vectors.dense(85470.09,14529.91,100000.0,14000.0,69.23076923076923,969230.77,164769.23)),
//				    (1,Vectors.dense(81111.11,13788.89,94900.0,23380.0,4.273504273504274,99914.53,16985.47)),
//				    (1,Vectors.dense(85470.09,14529.91,100000.0,14000.0,69.23076923076923,969230.77,164769.23)),
//				    (1,Vectors.dense(37606.84,6393.16,44000.0,16301.0,0.803077468374076,13090.97,2225.46)),
//				    (1,Vectors.dense(9435.9,1604.1,11040.0,201701,23076.923076923078,23076.92,3923.08)),
//				    (1,Vectors.dense(79261.54,13474.46,92736.0,1.0,3913.25,3913.25,234.79)),
//				    (1,Vectors.dense(79261.54,13474.46,92736.0,1.0,896.23,896.23,53.77)),
//				    (1,Vectors.dense(28580.64,4858.71,33439.35,1.0,9887.38,9887.38,296.62))			
//				)).toDF("label", "features")
				
          val testDF = sess.createDataFrame(Seq(
				    (0,Vectors.dense(3812,171,9401.71,1598.29,11000.0,201612,1)),
				    (0,Vectors.dense(4190,173,72200.0,12274.0,84474.0,201710,1)),
				    (0,Vectors.dense(7519,173,99999.99,3000.0,102999.99,201709,1)),
				    
				    (1,Vectors.dense(1951,173,19743.59,3356.41,23100.0,201612,1)),
				    (1,Vectors.dense(5219,173,41880.35,7119.65,49000.0,201705,1)),
				    (1,Vectors.dense(5189,173,1320.93,224.56,1545.49,201611,1)),	
				    (1,Vectors.dense(1779,173,21911.4,3724.94,25636.34,201611,0))
				)).toDF("label", "features")

//					val testDF = sess.createDataFrame(Seq(
//				    (1,Vectors.dense(10347616,225104,139712,5219,173,41880.35,7119.65,49000.0,201705,1,201710,0.825,3761.7197617197617,3103.42,527.58)),
//				    (1,Vectors.dense(10892655,162801,230845,1779,173,85470.09,14529.91,100000.0,201612,0,201705,1.0,9949.57264957265,9949.57,1691.43)),
//				    (1,Vectors.dense(10845244,30120,327910,5179,173,99863.25,16976.75,116840.0,201702,1,201708,0,0,9056.6,543.4)),
//				    
//				    (0,Vectors.dense(10003693,182442,334676,5299,173,47863.25,8136.75,56000.0,201704,1,201703,100.0,144.44444444444446,14444.44,2455.56)),
//				    (0,Vectors.dense(10003693,182442,334676,5299,173,47863.25,8136.75,56000.0,201704,1,201703,300.0,25.77777777777778,7733.33,1314.67)),
//				    (0,Vectors.dense(10003682,149224,188229,6312,159,1262.11,87.89,1350.0,201711,1,201612,6740.0,8.174275279616525,55094.62,9366.08))		    
//				)).toDF("label", "features")
				testDF.show()

				//创建临时视图
				testDF.createOrReplaceTempView("test")
				println("======================")
				
				//利用model对测试数据进行变化，得到新数据框，查询features", "label", "prediction方面值。
				
				val tested = model.transform(trainingDF).select("features", "label", "prediction");
				
				tested.show();
				//将分析的数据导入数据库
				
				
				import java.sql.DriverManager
			  tested.rdd.foreachPartition(
  			  it =>{
      				var url = "jdbc:mysql://localhost:3306/data?useUnicode=true&characterEncoding=utf8"
      				val conn= DriverManager.getConnection(url,"root","123456")
      				val pstat = conn.prepareStatement ("INSERT INTO `test` (`label`, `pre`,`zfbz`,`hydm`, `djzclx_dm`, "
      												  +"`kpyf`,`je`,`se`,`jshj`) "
      												  +"VALUES (?,?,?,?,?,?,?,?,?)")
      				for (obj <-it){
      					pstat.setString(1,obj.get(1).toString())
      					pstat.setString(2,obj.get(2).toString())
      					pstat.setString(3,obj.get(0).toString().split(",")(0).replaceAll("[\\[]", ""))
      					pstat.setString(4,obj.get(0).toString().split(",")(1))
      					pstat.setString(5,obj.get(0).toString().split(",")(2))
      					pstat.setString(6,obj.get(0).toString().split(",")(3))
      					pstat.setString(7,obj.get(0).toString().split(",")(4))
      					pstat.setString(8,obj.get(0).toString().split(",")(5))
      					pstat.setString(9,obj.get(0).toString().split(",")(6) .replaceAll("[\\]]", ""))
      					
      					
      					//pstat.setString(19,obj.get(2).toString().split(",")(16).replaceAll("[\\]]", ""))
      					pstat.addBatch
      				}
      				try{
      					pstat.executeBatch
      				}finally{
      					pstat.close
      					conn.close
      				}
    			 }
    		)	
    		

			}
}