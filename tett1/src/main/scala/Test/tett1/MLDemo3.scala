package Test.tett1

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegression

object MLDemo3 {
  
  def main(args: Array[String]): Unit = {
				val sess = SparkSession.builder().appName("ml").master("local[4]").getOrCreate();
				val sc = sess.sparkContext;
				val dataDir = "hdfs://weekend110:9000/user/hive/warehouse/nsr2_xfp"
				//定义样例类
        case class FP(fp_nid:String,nsr_id:String,gf_id:String,hydm:String,djzclx_dm:String,kydjrq:String,xgrq:String,
				    je:String,se:String,jshj:String,kpyf:String,kprq:String,zfbz:String,
				    label:String)

				//变换
				//0.fp_nid,1.nsr_id,2.gf_id,2.hydm,3.djzclx_dm,4.kydjrq,5.xgrq,6.je,7.se,8.jshj,9.kpyf,10.kprq,11.zfbz,12.date_key,13.hwmc,14.ggxh,15.dw,16.sl,17.dj,18.je je1,19.se1,20.spbm,21.label
			  val fpDataRDD = sc.textFile(dataDir).map(_.split("\001")).map(f => FP(f(0).toString, 
			  f(1).toString,f(2).toString,f(3).toString,f(4).toString,f(5).toString,f(6).toString, 
				f(7).toString, f(8).toString,f(9).toString,f(10).toString,f(11).toString,f(12).toString,
				f(13).toString))

				import sess.implicits._
			
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
				    if(f.zfbz.equals("N)")) 1 else 0,
				    f.hydm.replaceAll("[(]","").toDouble,
				    f.djzclx_dm.toDouble,
				    f.kpyf.toDouble,
				    strToDouble(f.je),
            strToDouble(f.se),
            strToDouble(f.jshj)
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
          val testDF = sess.createDataFrame(Seq(
				    (0,Vectors.dense(3812,171,9401.71,1598.29,11000.0,201612,1)),
				    (0,Vectors.dense(4190,173,72200.0,12274.0,84474.0,201710,1)),
				    (0,Vectors.dense(7519,173,99999.99,3000.0,102999.99,201709,1)),
				    
				    (1,Vectors.dense(1951,173,19743.59,3356.41,23100.0,201612,1)),
				    (1,Vectors.dense(5219,173,41880.35,7119.65,49000.0,201705,1)),
				    (1,Vectors.dense(5189,173,1320.93,224.56,1545.49,201611,1)),	
				    (1,Vectors.dense(1779,173,21911.4,3724.94,25636.34,201611,0))
				)).toDF("label", "features")
				
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