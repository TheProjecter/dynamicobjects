/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.cdo.test.dialogs;

import net.zehrer.no2.ui.dialogs.AbstractEMFDialog;

import org.eclipse.emf.cdo.common.model.CDOPackageInfo;
import org.eclipse.emf.cdo.common.model.CDOPackageRegistry;
import org.eclipse.emf.cdo.internal.ui.SharedIcons;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.net4j.util.ObjectUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPage;

public class CDOPackageListDialog extends AbstractEMFDialog {

	CDOSession session;

	public CDOPackageListDialog(IWorkbenchPage page, CDOSession session) {
		super(page);
		this.session = session;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		setTitle("Hello");
//		
//		setTitle(CDOItemProvider.getSessionLabel(session));
		setTitleImage(SharedIcons.getImage(SharedIcons.WIZBAN_PACKAGE_MANAGER));

//		Color color = composite.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
		
		// configure columns
//		addNoColumn("No.",viewer);
		
		TableViewerColumn column;
		
		column = addColumn(viewer, "URI", 400);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CDOPackageInfo packageInfo = (CDOPackageInfo) element;
				return packageInfo.getPackageURI();
			}
			
			@Override 
			public Image getImage(Object element) {
				CDOPackageInfo packageInfo = (CDOPackageInfo) element;;
				if (packageInfo != null) {
					switch (packageInfo.getPackageUnit().getType()) {
					case LEGACY:
						return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_LEGACY).createImage();
	
					case NATIVE:
						return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_NATIVE).createImage();
	
					case DYNAMIC:
						return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_DYNAMIC).createImage();
					}
				}
				return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_UNKNOWN).createImage();
			}

		});
	
		column = addColumn(viewer, "State", 100);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				CDOPackageInfo packageInfo = (CDOPackageInfo) element;
				return packageInfo.getPackageUnit().getState().toString();
			}
		});
		
		viewer.setContentProvider(new PackageInfoContentProvider(this.session));
		viewer.setInput(session);

		return composite;
	}
	
	
	public static class PackageInfoContentProvider implements IStructuredContentProvider {
		
		private static final Object[] NO_ELEMENTS = {};

		private CDOSession session;

		public PackageInfoContentProvider(CDOSession session) {
			this.session = session;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput instanceof EPackage) {
				if (!ObjectUtil.equals(this.session, newInput)) {
					this.session = (CDOSession) newInput;
				}
			}
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement != session) {
				return NO_ELEMENTS;
			}
			
			//Source: EMFUtil.getSortedRegistryEntries(session.getPackageRegistry());
			CDOPackageRegistry packageRegistry = session.getPackageRegistry();
			
			return packageRegistry.getPackageInfos();
		}
	}

}
