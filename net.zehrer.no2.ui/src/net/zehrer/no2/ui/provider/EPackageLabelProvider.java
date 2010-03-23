package net.zehrer.no2.ui.provider;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * @author Eike Stepper
 */
public class EPackageLabelProvider extends BaseLabelProvider implements ITableLabelProvider, IColorProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

//	/**
//	 * 
//	 */
//	private final GenericEMFDialog genericEMFDialog;
//
//	public EPackageLabelProvider(GenericEMFDialog genericEMFDialog) {
//		this.genericEMFDialog = genericEMFDialog;
//	}
//
//	public String getColumnText(Object element, int columnIndex) {
//		@SuppressWarnings("unchecked")
//		Map.Entry<String, Object> entry = (Entry<String, Object>) element;
//		CDOPackageInfo packageInfo = CDOModelUtil.getPackageInfo(entry.getValue(), this.genericEMFDialog.session.getPackageRegistry());
//		if (packageInfo != null) {
//			switch (columnIndex) {
//			case 0:
//				return null;
//			case 1:
//				return packageInfo.getPackageURI();
//
//			case 2:
//				return packageInfo.getPackageUnit().getState().toString();
//
//			case 3:
//				if (packageInfo.getPackageUnit().getType() == Type.UNKNOWN) {
//					return Messages.getString("PackageRegistryDialog.8"); //$NON-NLS-1$
//				}
//
//				return packageInfo.getPackageUnit().getType().toString();
//
//			case 4:
//				return packageInfo.getPackageUnit().getOriginalType().toString();
//			}
//		}
//
//		switch (columnIndex) {
//		case 1:
//			return entry.getKey();
//
//		default:
//			return ""; //$NON-NLS-1$
//		}
//	}
//
//	public Image getColumnImage(Object element, int columnIndex) {
//		if (columnIndex == 1) {
//			@SuppressWarnings("unchecked")
//			Map.Entry<String, Object> entry = (Entry<String, Object>) element;
//			CDOPackageInfo packageInfo = CDOModelUtil.getPackageInfo(entry.getValue(), this.genericEMFDialog.session.getPackageRegistry());
//			if (packageInfo != null) {
//				switch (packageInfo.getPackageUnit().getType()) {
//				case LEGACY:
//					return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_LEGACY).createImage();
//
//				case NATIVE:
//					return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_NATIVE).createImage();
//
//				case DYNAMIC:
//					return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_DYNAMIC).createImage();
//				}
//			}
//
//			return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_UNKNOWN).createImage();
//		}
//
//		return null;
//	}
//
//	public Color getBackground(Object element) {
//		return null;
//	}
//
//	public Color getForeground(Object element) {
//		@SuppressWarnings("unchecked")
//		Map.Entry<String, Object> entry = (Entry<String, Object>) element;
//		CDOPackageInfo packageInfo = CDOModelUtil.getPackageInfo(entry.getValue(), this.genericEMFDialog.session.getPackageRegistry());
//		if (packageInfo != null) {
//			return null;
//		}
//
//		return GenericEMFDialog.GRAY;
//	}
}