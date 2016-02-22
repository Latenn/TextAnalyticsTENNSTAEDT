

/* First created by JCasGen Mon Feb 22 14:40:13 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Feb 22 14:58:22 CET 2016
 * XML source: C:/Users/Lars/git/de.unidue.langtech.teaching.pp.Tennstaedt/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/ValueType.xml
 * @generated */
public class DetectedValue extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DetectedValue.class);
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
  protected DetectedValue() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DetectedValue(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DetectedValue(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public DetectedValue(JCas jcas, int begin, int end) {
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
  //* Feature: Value

  /** getter for Value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (DetectedValue_Type.featOkTst && ((DetectedValue_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "de.unidue.langtech.teaching.pp.type.DetectedValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DetectedValue_Type)jcasType).casFeatCode_Value);}
    
  /** setter for Value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (DetectedValue_Type.featOkTst && ((DetectedValue_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "de.unidue.langtech.teaching.pp.type.DetectedValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((DetectedValue_Type)jcasType).casFeatCode_Value, v);}    
  }

    