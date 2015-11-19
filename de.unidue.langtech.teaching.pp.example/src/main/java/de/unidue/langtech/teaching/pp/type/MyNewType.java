

/* First created by JCasGen Thu Nov 19 10:58:12 CET 2015 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Nov 19 11:29:00 CET 2015
 * XML source: C:/Users/Lars/git/de.unidue.langtech.teaching.pp.Tennstaedt/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/MyType.xml
 * @generated */
public class MyNewType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MyNewType.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MyNewType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MyNewType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MyNewType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MyNewType(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: countLetterE

  /** getter for countLetterE - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCountLetterE() {
    if (MyNewType_Type.featOkTst && ((MyNewType_Type)jcasType).casFeat_countLetterE == null)
      jcasType.jcas.throwFeatMissing("countLetterE", "de.unidue.langtech.teaching.pp.type.MyNewType");
    return jcasType.ll_cas.ll_getIntValue(addr, ((MyNewType_Type)jcasType).casFeatCode_countLetterE);}
    
  /** setter for countLetterE - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCountLetterE(int v) {
    if (MyNewType_Type.featOkTst && ((MyNewType_Type)jcasType).casFeat_countLetterE == null)
      jcasType.jcas.throwFeatMissing("countLetterE", "de.unidue.langtech.teaching.pp.type.MyNewType");
    jcasType.ll_cas.ll_setIntValue(addr, ((MyNewType_Type)jcasType).casFeatCode_countLetterE, v);}    
  }

    