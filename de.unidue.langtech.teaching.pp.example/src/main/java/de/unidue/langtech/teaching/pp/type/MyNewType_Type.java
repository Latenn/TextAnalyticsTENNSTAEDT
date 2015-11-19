
/* First created by JCasGen Thu Nov 19 10:58:12 CET 2015 */
package de.unidue.langtech.teaching.pp.type;

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
 * Updated by JCasGen Thu Nov 19 11:29:00 CET 2015
 * @generated */
public class MyNewType_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (MyNewType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = MyNewType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new MyNewType(addr, MyNewType_Type.this);
  			   MyNewType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new MyNewType(addr, MyNewType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MyNewType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.MyNewType");
 
  /** @generated */
  final Feature casFeat_countLetterE;
  /** @generated */
  final int     casFeatCode_countLetterE;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCountLetterE(int addr) {
        if (featOkTst && casFeat_countLetterE == null)
      jcas.throwFeatMissing("countLetterE", "de.unidue.langtech.teaching.pp.type.MyNewType");
    return ll_cas.ll_getIntValue(addr, casFeatCode_countLetterE);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCountLetterE(int addr, int v) {
        if (featOkTst && casFeat_countLetterE == null)
      jcas.throwFeatMissing("countLetterE", "de.unidue.langtech.teaching.pp.type.MyNewType");
    ll_cas.ll_setIntValue(addr, casFeatCode_countLetterE, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MyNewType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_countLetterE = jcas.getRequiredFeatureDE(casType, "countLetterE", "uima.cas.Integer", featOkTst);
    casFeatCode_countLetterE  = (null == casFeat_countLetterE) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_countLetterE).getCode();

  }
}



    