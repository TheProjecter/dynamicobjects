/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval.impl;

import net.zehrer.common.interval.EInterval;
import net.zehrer.common.interval.IntervalPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EInterval</b></em>'.
 * This class implement only a CLOSED interval.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.zehrer.common.interval.impl.EIntervalImpl#getLowerLimit <em>Lower Limit</em>}</li>
 *   <li>{@link net.zehrer.common.interval.impl.EIntervalImpl#getUpperLimit <em>Upper Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EIntervalImpl<T extends Comparable<T>> extends EObjectImpl implements EInterval<T> {
	/**
	 * The cached value of the '{@link #getLowerLimit() <em>Lower Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLimit()
	 * @generated
	 * @ordered
	 */
	protected T lowerLimit;

	/**
	 * The cached value of the '{@link #getUpperLimit() <em>Upper Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperLimit()
	 * @generated
	 * @ordered
	 */
	protected T upperLimit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EIntervalImpl() {
		super();
	}
	
	public EIntervalImpl(T lower, T upper) {
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
		return IntervalPackage.Literals.EINTERVAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public T getLowerLimit() {
		if (lowerLimit != null && ((EObject)lowerLimit).eIsProxy()) {
			InternalEObject oldLowerLimit = (InternalEObject)lowerLimit;
			lowerLimit = (T)eResolveProxy(oldLowerLimit);
			if (lowerLimit != oldLowerLimit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IntervalPackage.EINTERVAL__LOWER_LIMIT, oldLowerLimit, lowerLimit));
			}
		}
		return lowerLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T basicGetLowerLimit() {
		return lowerLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerLimit(T newLowerLimit) {
		T oldLowerLimit = lowerLimit;
		lowerLimit = newLowerLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntervalPackage.EINTERVAL__LOWER_LIMIT, oldLowerLimit, lowerLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public T getUpperLimit() {
		if (upperLimit != null && ((EObject)upperLimit).eIsProxy()) {
			InternalEObject oldUpperLimit = (InternalEObject)upperLimit;
			upperLimit = (T)eResolveProxy(oldUpperLimit);
			if (upperLimit != oldUpperLimit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IntervalPackage.EINTERVAL__UPPER_LIMIT, oldUpperLimit, upperLimit));
			}
		}
		return upperLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T basicGetUpperLimit() {
		return upperLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperLimit(T newUpperLimit) {
		T oldUpperLimit = upperLimit;
		upperLimit = newUpperLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntervalPackage.EINTERVAL__UPPER_LIMIT, oldUpperLimit, upperLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean intersects(EInterval<T> other) {
        int comparison = greaterOfLowerLimits(other).compareTo(lesserOfUpperLimits(other));
        if (comparison < 0)
            return true;
        if (comparison > 0)
            return false;
        return greaterOfLowerIncludedInIntersection(other) && lesserOfUpperIncludedInIntersection(other);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EInterval<T> intersect(EInterval<T> other) {
        T intersectLowerBound = greaterOfLowerLimits(other);
        T intersectUpperBound = lesserOfUpperLimits(other);
        if (intersectLowerBound.compareTo(intersectUpperBound) > 0)
            return emptyOfSameType();
        return newOfSameType(intersectLowerBound, intersectUpperBound);

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isBelow(T value) {
//        if (!hasUpperLimit()) return false;
        int comparison = getUpperLimit().compareTo(value);
        return comparison < 0 || (comparison == 0 && !includesUpperLimit());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAbove(T value) {
//        if (!hasLowerLimit()) return false;
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
            return equals((EInterval<T>)other);
        } catch(ClassCastException ex) {
            return false;
        }
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(EInterval<T> other) {
        if (other == null) return false;
        
//        boolean thisEmpty = this.isEmpty();
////        boolean otherEmpty = other.isEmpty();
//        if (thisEmpty & otherEmpty)
//            return true;
//        if (thisEmpty ^ otherEmpty)
//            return false;

//        boolean thisSingle = this.isSingleElement();
//        boolean otherSingle = other.isSingleElement();
//        if (thisSingle & otherSingle)
//            return this.lowerLimit().equals(other.lowerLimit());
//        if (thisSingle ^ otherSingle)
//            return false;

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
	public boolean includes(T value) {
        return !this.isBelow(value) && !this.isAbove(value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int compareTo(EInterval<T> other) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}


	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IntervalPackage.EINTERVAL__LOWER_LIMIT:
				if (resolve) return getLowerLimit();
				return basicGetLowerLimit();
			case IntervalPackage.EINTERVAL__UPPER_LIMIT:
				if (resolve) return getUpperLimit();
				return basicGetUpperLimit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IntervalPackage.EINTERVAL__LOWER_LIMIT:
				setLowerLimit((T)newValue);
				return;
			case IntervalPackage.EINTERVAL__UPPER_LIMIT:
				setUpperLimit((T)newValue);
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
			case IntervalPackage.EINTERVAL__LOWER_LIMIT:
				setLowerLimit((T)null);
				return;
			case IntervalPackage.EINTERVAL__UPPER_LIMIT:
				setUpperLimit((T)null);
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
			case IntervalPackage.EINTERVAL__LOWER_LIMIT:
				return lowerLimit != null;
			case IntervalPackage.EINTERVAL__UPPER_LIMIT:
				return upperLimit != null;
		}
		return super.eIsSet(featureID);
	}


	// ------

	private T greaterOfLowerLimits(EInterval<T> other) {
        if (getLowerLimit() == null) {
            return other.getLowerLimit();
        }
        int lowerComparison = getLowerLimit().compareTo(other.getLowerLimit());
        if (lowerComparison >= 0)
            return this.getLowerLimit();
        return other.getLowerLimit();
    }

    private T lesserOfUpperLimits(EInterval<T> other) {
        if (getUpperLimit() == null) {
            return other.getUpperLimit();
        }
        int upperComparison = getUpperLimit().compareTo(other.getUpperLimit());
        if (upperComparison <= 0)
            return this.getUpperLimit();
        return other.getUpperLimit();
    }
	
    private boolean greaterOfLowerIncludedInIntersection(EInterval<T> other) {
        T limit = greaterOfLowerLimits(other);
        return this.includes(limit) && other.includes(limit);
    }

    private boolean lesserOfUpperIncludedInIntersection(EInterval<T> other) {
        T limit = lesserOfUpperLimits(other);
        return this.includes(limit) && other.includes(limit);
    }
    
    private boolean includesLowerLimit() {
        return true; // lowerLimitObject.isClosed();
    }
    
    private boolean includesUpperLimit() {
        return true; // upperLimitObject.isClosed();
    }
    
    // ------
    
    abstract protected EInterval<T> newOfSameType(T lower, T upper); //
//        return new EIntervalImpl<T>(lower,upper);

    public EInterval<T> emptyOfSameType() {
        return newOfSameType(getLowerLimit(), getLowerLimit());
    }
	

    
} //EIntervalImpl
