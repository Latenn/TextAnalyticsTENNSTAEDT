

/* First created by JCasGen Thu Nov 19 11:31:40 CET 2015 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Nov 19 11:31:40 CET 2015
 * XML source: C:/Users/Lars/git/de.unidue.langtech.teaching.pp.Tennstaedt/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/LetterACounter.xml
 * @generated */
public class LetterACounter extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LetterACounter.class);
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
  protected LetterACounter() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LetterACounter(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LetterACounter(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LetterACounter(JCas jcas, int begin, int end) {
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
  //* Feature: countLetterA

  /** getter for countLetterA - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCountLetterA() {
    if (LetterACounter_Type.featOkTst && ((LetterACounter_Type)jcasType).casFeat_countLetterA == null)
      jcasType.jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.LetterACounter");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LetterACounter_Type)jcasType).casFeatCode_countLetterA);}
    
  /** setter for countLetterA - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCountLetterA(int v) {
    if (LetterACounter_Type.featOkTst && ((LetterACounter_Type)jcasType).casFeat_countLetterA == null)
      jcasType.jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.LetterACounter");
    jcasType.ll_cas.ll_setIntValue(addr, ((LetterACounter_Type)jcasType).casFeatCode_countLetterA, v);}    
  }

    