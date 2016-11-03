package co.com.scalatraining.syntax

import org.scalatest.{FunSuite, Tag}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

class SyntaxSuite extends FunSuite{

  test("Un var debe permitir realizar asignaciones"){
    var x = 0
    assert(x == 0)
    x = 2
    assert(x == 2)
  }

  test("Un val no debe permitir realizar asignaciones"){
    val x = 0
    assert(x == 0)
    assertDoesNotCompile("x = 1")
  }

  test("Los tipos en Scala son inferidos por el compilador"){
    // Fijate como no hay que decir de qué tipo es x
    val x = 0
    assert(x == 0)

    // Aunque tambien lo puedes hacer explicito si asi lo dquieres
    val y: String = "0"
    assert(y == "0")
    
    var strong = "0"
    assertDoesNotCompile ("strong = 1")
  }

  test("Un object puede tener funciones miembro"){

    object obj {
      def f1(a: Int) = a + 1
      def f2(a: Int) = a + 2
    }


    val res = obj.f1(1)

    assert(res == 2)

  }

  test("Un class se puede comoportar como un class tradicional"){

    //los parametros de contruccion se definen entre parentesis luego nombre y tipo
    class MyClass(a:Int){
      def f1 = a + 1
      def f2 = a + 2
    }

    // A una class se le debe instanciar con new pasándole los atributos que define para su construccion
    val mc = new MyClass(1)
    val res = mc.f1

    assert(res == 2)  

  }

  test("Un class puede mutar su estado"){
    class MyClass(a:Int){

      var r = 0

      def f1 = {
        r = r + 2
        a + 1
      }

      def f2 = a + 2
    }

    // A una class se le debe instanciar con new pasándole los atributos que define para su construccion
    val mc = new MyClass(1)
    assert(mc.r == 0)
    val res = mc.f1
    assert(mc.r == 2)
  }

  test("Un case es una clase normal para usos especificos, tiene constructor y serializador a toString"){

    case class MyCaseClass(a:Int, b:Int) {
      def f1(a:Int) = a + 1
    }

    // Se puede instanciar de forma normal
    val mcc1 = new MyCaseClass(1, 2)
    assert(mcc1.f1(1) == 2)

    // Se puede instanciar sin new
    val mcc2 = MyCaseClass(1,2)
    assert(mcc2.f1(1) == 2)

    //Que pasa si intentamos println(mcc2) ?

    // Pregunta cuáles son esos casos específicos


  }

  test("Un trait puede tener solo definiciones"){
    trait MyTrait {
      def f1(a:Int)
    }

    class MyClass extends MyTrait {
      override def f1(a:Int) = ???
    }

    assertThrows[NotImplementedError]{
      val mc = new MyClass
      mc.f1(1)
    }

  }

  test("Un trait puede tener tambien implementaciones"){
    trait MyTrait {
      def f1(a:Int) = a + 1
    }

    class MyClass extends MyTrait

    val mc = new MyClass
    val res = mc.f1(1)
    assert(res == 2)

  }

}
