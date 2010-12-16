/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval.impl;

import net.zehrer.common.interval.EIntInterval;
import net.zehrer.common.interval.IntervalPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EInt Interval</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.zehrer.common.interval.impl.EIntIntervalImpl#getLowerLimit <em>Lower Limit</em>}</li>
 *   <li>{@link net.zehrer.common.interval.impl.EIntIntervalImpl#getUpperLimit <em>Upper Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EIntIntervalImpl extends EObjectImpl implements EIntInterval {
	/**
	 * The default value of the '{@link #getLowerLimit() <em>Lower Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LOWER_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLowerLimit() <em>Lower Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLimit()
	 * @generated
	 * @ordered
	 */
	protected Integer lowerLimit = LOWER_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperLimit() <em>Upper Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Integer UPPER_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperLimit() <em>Upper Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperLimit()
	 * @generated
	 * @ordered
	 */
	protected Integer upperLimit = UPPER_LIMIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EIntIntervalImpl() {
		super();
	}

	public EIntIntervalImpl(Integer lower, Integer upper) {
		setLowerLimit(lower);
		setUpperLimit(upper);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntervalPackage.Literals.EINT_INTERVAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerLimit(Integer newLowerLimit) {
		Integer oldLowerLimit = lowerLimit;
		lowerLimit = newLowerLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntervalPackage.EINT_INTERVAL__LOWER_LIMIT, oldLowerLimit, lowerLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getUpperLimit() {
		return upperLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperLimit(Integer newUpperLimit) {
		Integer oldUpperLimit = upperLimit;
		upperLimit = newUpperLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntervalPackage.EINT_INTERVAL__UPPER_LIMIT, oldUpperLimit, upperLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean intersects(EIntInterval other) {
        int comparison = greaterOfLowerLimits(other).compareTo(lesserOfUpperLimits(other));
        if (comparison < 0)
            return true;
        if (comparison > 0)
            return false;
        return greaterOfLowerIncludedInIntersection(other) && lesserOfUpperIncludedInIntersection(other);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns null for an empty set 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EIntInterval intersect(EIntInterval other) {
		Integer intersectLowerBound = greaterOfLowerLimits(other);
		Integer intersectUpperBound = lesserOfUpperLimits(other);
        if (intersectLowerBound.compareTo(intersectUpperBound) > 0)
            return null; // emptyOfSameType();
        return newOfSameType(intersectLowerBound, intersectUpperBound);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isBelow(Integer value) {
//      if (!hasUpperLimit()) return false;
        int comparison = getUpperLimit().compareTo(value);
        return comparison < 0 || (comparison == 0 && !includesUpperLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAbove(Integer value) {
//      if (!hasLowerLimit()) return false;
        int comparison = getLowerLimit().compareTo(value);
        return comparison > 0 || (comparison == 0 && !includesLowerLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
    @SuppressWarnings("unchecked")
	public boolean equals(Object other) {
        try {
            return equals((EIntInterval)other);
        } catch(ClassCastException ex) {
            return false;
        }
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(EIntInterval other) {
        if (other == null) return false;
        
//      boolean thisEmpty = this.isEmpty();
////      boolean otherEmpty = other.isEmpty();
//      if (thisEmpty & otherEmpty)
//          return true;
//      if (thisEmpty ^ otherEmpty)
//          return false;

//      boolean thisSingle = this.isSingleElement();
//      boolean otherSingle = other.isSingleElement();
//      if (thisSingle & otherSingle)
//          return this.lowerLimit().equals(other.lowerLimit());
//      if (thisSingle ^ otherSingle)
//          return false;

      return compareTo(other) == 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int hashCode() {
		 return getLowerLimit().hashCode() ^ getUpperLimit().hashCode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EIntInterval leftComplementRelativeTo(EIntInterval other) {
		if (this.includes(lesserOfLowerLimits(other)))
	            return null;
//		if (getLowerLimit().equals(other.getLowerLimit()) && !other.includesLowerLimit())
//	            return null;
	    return newOfSameType(other.getLowerLimit(), this.getLowerLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public EIntInterval rightComplementRelativeTo(EIntInterval other) {
        if (this.includes(greaterOfUpperLimits(other)))
            return null;
//        if (getUpperLimit().equals(other.getUpperLimit()) && !other.includesUpperLimit())
//            return null;
        return newOfSameType(this.getUpperLimit(), other.getUpperLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean includes(Integer value) {
        return !this.isBelow(value) && !this.isAbove(value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Is this subset of value?
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSubsetOf(EIntInterval value) {
		return (this.getLowerLimit().compareTo(value.getLowerLimit()) >= 0 
				&& this.getUpperLimit().compareTo(value.getUpperLimit()) <= 0);

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int compareTo(EIntInterval other) {
        if (!getUpperLimit().equals(other.getUpperLimit()))
            return getUpperLimit().compareTo(other.getUpperLimit());
        
//        if (includesLowerLimit() && !other.includesLowerLimit())
//            return -1;
//        if (!includesLowerLimit() && other.includesLowerLimit())
//            return 1;
        return getLowerLimit().compareTo(other.getLowerLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IntervalPackage.EINT_INTERVAL__LOWER_LIMIT:
				return getLowerLimit();
			case IntervalPackage.EINT_INTERVAL__UPPER_LIMIT:
				return getUpperLimit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IntervalPackage.EINT_INTERVAL__LOWER_LIMIT:
				setLowerLimit((Integer)newValue);
				return;
			case IntervalPackage.EINT_INTERVAL__UPPER_LIMIT:
				setUpperLimit((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IntervalPackage.EINT_INTERVAL__LOWER_LIMIT:
				setLowerLimit(LOWER_LIMIT_EDEFAULT);
				return;
			case IntervalPackage.EINT_INTERVAL__UPPER_LIMIT:
				setUpperLimit(UPPER_LIMIT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IntervalPackage.EINT_INTERVAL__LOWER_LIMIT:
				return LOWER_LIMIT_EDEFAULT == null ? lowerLimit != null : !LOWER_LIMIT_EDEFAULT.equals(lowerLimit);
			case IntervalPackage.EINT_INTERVAL__UPPER_LIMIT:
				return UPPER_LIMIT_EDEFAULT == null ? upperLimit != null : !UPPER_LIMIT_EDEFAULT.equals(upperLimit);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lowerLimit: ");
		result.append(lowerLimit);
		result.append(", upperLimit: ");
		result.append(upperLimit);
		result.append(')');
		return result.toString();
	}
	
	// ------
	
    private Integer lesserOfLowerLimits(EIntInterval other) {
        if (getLowerLimit() == null) {
            return null;
        }
        int lowerComparison = getLowerLimit().compareTo( other.getLowerLimit());
        if (lowerComparison <= 0)
            return this.getLowerLimit();
        return other.getLowerLimit();
    }

	private Integer greaterOfLowerLimits(EIntInterval other) {
        if (getLowerLimit() == null) {
            return other.getLowerLimit();
        }
        int lowerComparison = getLowerLimit().compareTo(other.getLowerLimit());
        if (lowerComparison >= 0)
            return this.getLowerLimit();
        return other.getLowerLimit();
    }

	
    private Integer lesserOfUpperLimits(EIntInterval other) {
        if (getUpperLimit() == null) {
            return other.getUpperLimit();
        }
        int upperComparison = getUpperLimit().compareTo(other.getUpperLimit());
        if (upperComparison <= 0)
            return this.getUpperLimit();
        return other.getUpperLimit();
    }
    
    private Integer greaterOfUpperLimits(EIntInterval other) {
        if (getUpperLimit() == null) {
            return null;
        }
        int upperComparison = getUpperLimit().compareTo(other.getUpperLimit());
        if (upperComparison >= 0)
            return this.getUpperLimit();
        return other.getUpperLimit();
    }
	
    private boolean greaterOfLowerIncludedInIntersection(EIntInterval other) {
        Integer limit = greaterOfLowerLimits(other);
        return this.includes(limit) && other.includes(limit);
    }

    private boolean lesserOfUpperIncludedInIntersection(EIntInterval other) {
    	Integer limit = lesserOfUpperLimits(other);
        return this.includes(limit) && other.includes(limit);
    }
    
    private boolean includesLowerLimit() {
        return true; // lowerLimitObject.isClosed();
    }
    
    private boolean includesUpperLimit() {
        return true; // upperLimitObject.isClosed();
    }
    
    // ------
    
    protected EIntInterval newOfSameType(Integer lower, Integer upper) {
    	return new EIntIntervalImpl(lower,upper);
    }

    public EIntInterval emptyOfSameType() {
        return newOfSameType(getLowerLimit(), getLowerLimit());
    }


} //EIntIntervalImpl
