
/* First created by JCasGen Fri Feb 19 10:30:29 CET 2016 */
package de.unidue.de.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Feb 19 10:38:51 CET 2016
 * @generated */
public class ValueType_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ValueType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ValueType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ValueType(addr, ValueType_Type.this);
  			   ValueType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ValueType(addr, ValueType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ValueType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.de.langtech.teaching.pp.type.ValueType");
 
  /** @generated */
  final Feature casFeat_GoldValue;
  /** @generated */
  final int     casFeatCode_GoldValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getGoldValue(int addr) {
        if (featOkTst && casFeat_GoldValue == null)
      jcas.throwFeatMissing("GoldValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_GoldValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGoldValue(int addr, String v) {
        if (featOkTst && casFeat_GoldValue == null)
      jcas.throwFeatMissing("GoldValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    ll_cas.ll_setStringValue(addr, casFeatCode_GoldValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_DetectedValue;
  /** @generated */
  final int     casFeatCode_DetectedValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDetectedValue(int addr) {
        if (featOkTst && casFeat_DetectedValue == null)
      jcas.throwFeatMissing("DetectedValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DetectedValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDetectedValue(int addr, String v) {
        if (featOkTst && casFeat_DetectedValue == null)
      jcas.throwFeatMissing("DetectedValue", "de.unidue.de.langtech.teaching.pp.type.ValueType");
    ll_cas.ll_setStringValue(addr, casFeatCode_DetectedValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ValueType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_GoldValue = jcas.getRequiredFeatureDE(casType, "GoldValue", "uima.cas.String", featOkTst);
    casFeatCode_GoldValue  = (null == casFeat_GoldValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_GoldValue).getCode();

 
    casFeat_DetectedValue = jcas.getRequiredFeatureDE(casType, "DetectedValue", "uima.cas.String", featOkTst);
    casFeatCode_DetectedValue  = (null == casFeat_DetectedValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DetectedValue).getCode();

  }
}



    