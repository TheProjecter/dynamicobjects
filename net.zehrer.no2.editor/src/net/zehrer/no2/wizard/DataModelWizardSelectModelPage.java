/*******************************************************************************
 * Copyright (c) 2009 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *     
 * Severl other classes    
 *     ModelConverterURIPage.java
 *     ModelImporterDetailPage.java
 *******************************************************************************/

package net.zehrer.no2.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardResourceImportPage;



// TODO: check manual added UTI's
// TODO: check model befor finish ...
public class DataModelWizardSelectModelPage extends WizardResourceImportPage {

	protected Text uriText;
	protected List<String> fileExtensions = new ArrayList<String>();

	protected Button browseFileSystemButton;
	protected Button browseWorkspaceButton;
//	protected Button loadButton;

	protected DataModelWizardSelectModelPage(IStructuredSelection selection) {
		// TODO: name???
		super("Page Name", selection);
		addExteinsion("ecore");
	}

	public void addExteinsion(String ext) {
		this.fileExtensions.add(ext);
	}

	public void createControl(Composite parent) {

		initializeDialogUnits(parent);

		Composite composite = new Composite(parent, SWT.NONE);

		// composite.setLayout(new GridLayout());
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 8;
		composite.setLayout(layout);

		composite.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_VERTICAL));
		// composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
		// | GridData.HORIZONTAL_ALIGN_FILL));

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setFont(parent.getFont());

		createURIControl(composite);
		// addControl(composite);

		// createFileSelectionGroup(composite);


		// restoreWidgetValues();
		// updateWidgetEnablements();
		// setPageComplete(determinePageCompletion());

		setErrorMessage(null); // should not initially have error message
		setControl(composite);

	}

	protected void createURIControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL));
		{
			FormLayout layout = new FormLayout();
			layout.marginTop = 10;
			layout.spacing = 10;
			composite.setLayout(layout);
		}

		Label uriLabel = new Label(composite, SWT.LEFT);
		{
			FormData data = new FormData();
			data.left = new FormAttachment(0);
			uriLabel.setLayoutData(data);
		}
		//TODO: Label text
		uriLabel.setText("File:");

		Composite uriComposite = new Composite(composite, SWT.NONE);
		{
			FormData data = new FormData();
			data.top = new FormAttachment(uriLabel, 5);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			uriComposite.setLayoutData(data);

			GridLayout layout = new GridLayout(2, false);
			layout.marginTop = -5;
			layout.marginLeft = -5;
			layout.marginRight = -5;
			uriComposite.setLayout(layout);
		}

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		{
			FormData data = new FormData();
			data.top = new FormAttachment(uriLabel, 0, SWT.CENTER);
			data.left = new FormAttachment(uriLabel, 0);
			data.right = new FormAttachment(100);
			buttonComposite.setLayoutData(data);

			FormLayout layout = new FormLayout();
			layout.marginTop = 0;
			layout.marginBottom = 0;
			layout.marginLeft = 0;
			layout.marginRight = 0;
			layout.spacing = 5;
			buttonComposite.setLayout(layout);
		}

		browseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
		browseFileSystemButton.setText(getBrowseFileSystemButtonLabel());
		browseFileSystemButton.addListener(SWT.Selection, this);

		browseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
		browseWorkspaceButton.setText(getBrowseWorkspaceButtonLabel());
		browseWorkspaceButton.addListener(SWT.Selection, this);

		{
			FormData data = new FormData();
			data.right = new FormAttachment(browseWorkspaceButton);
			browseFileSystemButton.setLayoutData(data);
		}

		{
			FormData data = new FormData();
			data.right = new FormAttachment(100);
			browseWorkspaceButton.setLayoutData(data);
		}

		uriText = new Text(uriComposite, SWT.SINGLE | SWT.BORDER);
		setURIText(getURITextInitialValue());
		if (uriText.getText().length() > 0) {
			uriText.selectAll();
		}
		uriText.addListener(SWT.Modify, this);

		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			if (uriComposite.getChildren().length == 1) {
				gridData.horizontalSpan = 2;
			}
			uriText.setLayoutData(gridData);
		}
	}


	/**
	 * Creates the import destination specification controls.
	 * 
	 * @param parent
	 *            the parent control
	 */
	// protected final void createSourceGroup(Composite parent) {
	// // container specification group
	// Composite sourceGroup = new Composite(parent, SWT.NONE);
	// GridLayout layout = new GridLayout();
	// layout.numColumns = 3;
	// sourceGroup.setLayout(layout);
	// sourceGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
	// | GridData.GRAB_HORIZONTAL));
	// sourceGroup.setFont(parent.getFont());
	//
	// // container label
	// Label resourcesLabel = new Label(sourceGroup, SWT.NONE);
	// // TODO: text
	// resourcesLabel.setText("File:");
	// resourcesLabel.setFont(parent.getFont());
	//
	// // container name entry field
	// sourceNameField = new Text(sourceGroup, SWT.SINGLE | SWT.BORDER);
	// sourceNameField.addListener(SWT.Modify, this);
	//
	// GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
	// | GridData.GRAB_HORIZONTAL);
	// data.widthHint = SIZING_TEXT_FIELD_WIDTH;
	// sourceNameField.setLayoutData(data);
	// sourceNameField.setFont(parent.getFont());
	//
	// // container browse button
	// sourceBrowseButton = new Button(sourceGroup, SWT.PUSH);
	// sourceBrowseButton
	// .setText(IDEWorkbenchMessages.WizardImportPage_browse2);
	// sourceBrowseButton.setLayoutData(new GridData(
	// GridData.HORIZONTAL_ALIGN_FILL));
	// sourceBrowseButton.addListener(SWT.Selection, this);
	// sourceBrowseButton.setFont(parent.getFont());
	// setButtonLayoutData(sourceBrowseButton);
	//
	// }



	@Override
	public void handleEvent(Event event) {
		if (event.type == SWT.Modify && event.widget == uriText) {
			uriTextModified(uriText.getText().trim());
		} else if (event.type == SWT.Selection
				&& event.widget == browseFileSystemButton) {
			browseFileSystem();
		} else if (event.type == SWT.Selection
				&& event.widget == browseWorkspaceButton) {
			browseWorkspace();
		} else {
			super.handleEvent(event);
		}

		getContainer().updateButtons();
	}

	@Override
	public void dispose() {
		if (uriText != null) {
			uriText.removeListener(SWT.Modify, this);
			uriText = null;
		}
		if (browseFileSystemButton != null) {
			browseFileSystemButton.removeListener(SWT.Selection, this);
			browseFileSystemButton = null;
		}
		if (browseWorkspaceButton != null) {
			browseWorkspaceButton.removeListener(SWT.Selection, this);
			browseWorkspaceButton = null;
		}
		// if (loadButton != null) {
		// loadButton.removeListener(SWT.Selection, this);
		// loadButton = null;
		// }

		super.dispose();
	}

	protected void setURIText(String newUri) {
		String uri = newUri.trim();
		StringBuffer oldURI = new StringBuffer(uriText.getText());
		if (!uri.equals(oldURI)) {
			uriText.setText(uri.trim());
		}
	}

	protected void uriTextModified(String text) {
		setErrorMessage(null);
		setMessage(null);
	}

	protected boolean browseFileSystem() {
		FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN
				| SWT.SINGLE);
		fileDialog.setFilterExtensions(getFilterExtensions());

		// Eclipse provide a warning for "Dead code" :)
//		URI modelURI = null; // getModelImporter().getFirstModelLocationURI(true);
//		if (modelURI != null) {
//			fileDialog.setFileName(modelURI.toFileString());
//		}

		if (fileDialog.open() != null && fileDialog.getFileNames().length > 0) {
			String[] fileNames = fileDialog.getFileNames();
			StringBuffer text = new StringBuffer();
			for (int i = 0; i < fileNames.length; ++i) {
				String filePath = fileDialog.getFilterPath() + File.separator
						+ fileNames[i];
				text.append(URI.createFileURI(filePath).toString());
				text.append(" ");
			}
			setURIText(text.toString());

			return true;
		}
		return false;
	}

	protected boolean browseWorkspace() {

		ViewerFilter extensionFilter = new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return !(element instanceof IFile)
						|| getFileExtensions().contains(
								((IFile) element).getFileExtension());
			}
		};

		IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(),
				null, null, false, null, Collections
						.singletonList(extensionFilter));

		if (files.length > 0) {
			setURIText(URI.createPlatformResourceURI(files[0].getFullPath().toString(),true).toString());
			return true;
		}
		return false;
	}



	protected String[] getFilterExtensions() {
		String[] extensions = new String[this.fileExtensions.size()];
		return this.fileExtensions.toArray(extensions);
	}

	public List<String> getFileExtensions() {
		return fileExtensions;
	}


	protected String getURITextInitialValue() {
		return "";
	}

	protected String getBrowseFileSystemButtonLabel() {
		return "Browse File Sysem ...";
		// return ConverterPlugin.INSTANCE
		// .getString("_UI_BrowseFileSystemFile_label");
	}

	protected String getBrowseWorkspaceButtonLabel() {
		return "Browse Workspace ...";
		// return
		// ConverterPlugin.INSTANCE.getString("_UI_BrowseWorkspace_label");
	}

	@Override
	public boolean isPageComplete() {
		return super.isPageComplete() && uriText.toString() != "";
		// && !getModelImporter().getEPackages().isEmpty()

	}

	@Override
	protected void createSourceGroup(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ITreeContentProvider getFileProvider() {

		return null; // new WorkbenchContentProvider();
	}

	@Override
	protected ITreeContentProvider getFolderProvider() {

		return null; // new WorkbenchContentProvider();
	}
}
