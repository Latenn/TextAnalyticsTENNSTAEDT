

/* First created by JCasGen Fri Feb 19 10:30:29 CET 2016 */
package de.unidue.de.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Feb 19 10:38:51 CET 2016
 * XML source: C:/Users/Lars/git/de.unidue.langtech.teaching.pp.Tennstaedt/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/ValueType.xml
 * @generated */
public class ValueType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ValueType.class);
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
  protected ValueType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ValueType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ValueType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ValueType(JCas jcas, int begin, int end) {
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
  //* Feature: GoldValue

  /** getter for GoldValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGoldValue() {
    if (ValueType_Type.featOkTst && ((ValueType_Type)jcasType).casFeat_GoldValue == null)
      jcasType.jcas.throwFeatMissing("GoldValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValueType_Type)jcasType).casFeatCode_GoldValue);}
    
  /** setter for GoldValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGoldValue(String v) {
    if (ValueType_Type.featOkTst && ((ValueType_Type)jcasType).casFeat_GoldValue == null)
      jcasType.jcas.throwFeatMissing("GoldValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValueType_Type)jcasType).casFeatCode_GoldValue, v);}    
   
    
  //*--------------*
  //* Feature: DetectedValue

  /** getter for DetectedValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDetectedValue() {
    if (ValueType_Type.featOkTst && ((ValueType_Type)jcasType).casFeat_DetectedValue == null)
      jcasType.jcas.throwFeatMissing("DetectedValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ValueType_Type)jcasType).casFeatCode_DetectedValue);}
    
  /** setter for DetectedValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDetectedValue(String v) {
    if (ValueType_Type.featOkTst && ((ValueType_Type)jcasType).casFeat_DetectedValue == null)
      jcasType.jcas.throwFeatMissing("DetectedValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    jcasType.ll_cas.ll_setStringValue(addr, ((ValueType_Type)jcasType).casFeatCode_DetectedValue, v);}    
  }

    