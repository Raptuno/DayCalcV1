package core;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.core.databinding.beans.PojoProperties;

public class SwtMain {
	private DataBindingContext m_bindingContext;

	protected Shell shlCalculadoraDeDas;
	private DateTime dtmDaCalculo;
	private Group grpCalcularDesdeHoy;
	private Text txtRes;
	private Date d;
	private Button btnNo;
	private Text txtDa;
	private Text txtMes;
	private Text txtAo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					SwtMain window = new SwtMain();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCalculadoraDeDas.open();
		shlCalculadoraDeDas.layout();
		while (!shlCalculadoraDeDas.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCalculadoraDeDas = new Shell();
		shlCalculadoraDeDas.setToolTipText("D\u00EDas para calcular desde hoy");
		shlCalculadoraDeDas.setSize(379, 212);
		shlCalculadoraDeDas.setText("Calculadora de d\u00EDas");
		shlCalculadoraDeDas.setLayout(new FormLayout());
		
		Group grpElegirDa = new Group(shlCalculadoraDeDas, SWT.NONE);
		grpElegirDa.setText("Elegir d\u00EDa");
		grpElegirDa.setLayout(new GridLayout(2, false));
		FormData fd_grpElegirDa = new FormData();
		fd_grpElegirDa.top = new FormAttachment(0, 10);
		fd_grpElegirDa.left = new FormAttachment(0, 10);
		grpElegirDa.setLayoutData(fd_grpElegirDa);
		
		dtmDaCalculo = new DateTime(grpElegirDa, SWT.BORDER);
		
		Spinner spnDas = new Spinner(grpElegirDa, SWT.BORDER);
		
		grpCalcularDesdeHoy = new Group(grpElegirDa, SWT.NONE);
		grpCalcularDesdeHoy.setText("\u00BFCalcular desde hoy?");
		grpCalcularDesdeHoy.setLayout(new GridLayout(1, false));
		
		Button btnS = new Button(grpCalcularDesdeHoy, SWT.RADIO);
		btnS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnS.getSelection()) {
					getDateTime().setDate(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth());
					System.out.println(getDateTime().getMonth());
				}
			}
		});
		btnS.setSelection(true);
		btnS.setText("S\u00ED");
		
		btnNo = new Button(grpCalcularDesdeHoy, SWT.RADIO);
		btnNo.setText("No");
		new Label(grpElegirDa, SWT.NONE);
		
		Group grpResultado = new Group(shlCalculadoraDeDas, SWT.NONE);
		grpResultado.setText("Resultado");
		grpResultado.setLayout(new GridLayout(2, false));
		FormData fd_grpResultado = new FormData();
		fd_grpResultado.top = new FormAttachment(grpElegirDa, 0, SWT.TOP);
		fd_grpResultado.left = new FormAttachment(grpElegirDa, 6);
		grpResultado.setLayoutData(fd_grpResultado);
		
		Label lblDasHastaLa = new Label(grpResultado, SWT.NONE);
		lblDasHastaLa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDasHastaLa.setText("Fecha: ");
		
		txtRes = new Text(grpResultado, SWT.NONE);
		txtRes.setText("Resultado");
		txtRes.setEnabled(false);
		txtRes.setEditable(false);
		txtRes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(grpResultado, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblDa = new Label(composite_1, SWT.NONE);
		lblDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDa.setText("D\u00EDa: ");
		
		txtDa = new Text(composite_1, SWT.BORDER);
		txtDa.setEnabled(false);
		txtDa.setEditable(false);
		txtDa.setText("D\u00EDa");
		txtDa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblMes = new Label(composite_1, SWT.NONE);
		lblMes.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMes.setText("Mes");
		
		txtMes = new Text(composite_1, SWT.BORDER);
		txtMes.setEnabled(false);
		txtMes.setEditable(false);
		txtMes.setText("Mes");
		txtMes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAo = new Label(composite_1, SWT.NONE);
		lblAo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAo.setText("A\u00F1o");
		
		txtAo = new Text(composite_1, SWT.BORDER);
		txtAo.setEnabled(false);
		txtAo.setEditable(false);
		txtAo.setText("A\u00F1o");
		txtAo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpResultado, SWT.NONE);
		
		Composite composite = new Composite(shlCalculadoraDeDas, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(grpElegirDa, 6);
		fd_composite.left = new FormAttachment(grpElegirDa, 0, SWT.LEFT);
		composite.setLayoutData(fd_composite);
		
		Button btnCalcularDas = new Button(composite, SWT.NONE);
		btnCalcularDas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				d=java.util.Calendar.getInstance().getTime();
				System.out.println(d.getDate());
				
				txtRes.setText(getDateTime().toString());
			}
		});
		btnCalcularDas.setText("Calcular d\u00EDas");
		
		Button btnSalir = new Button(composite, SWT.NONE);
		btnSalir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime.getRuntime().exit(0);
			}
		});
		btnSalir.setText("Salir");
		m_bindingContext = initDataBindings();

	}
	public DateTime getDateTime() {
		return dtmDaCalculo;
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeEnabledDtmDaCalculoObserveWidget = WidgetProperties.enabled().observe(dtmDaCalculo);
		IObservableValue observeSelectionBtnNoObserveWidget = WidgetProperties.selection().observe(btnNo);
		bindingContext.bindValue(observeEnabledDtmDaCalculoObserveWidget, observeSelectionBtnNoObserveWidget, null, null);
		//
		IObservableValue observeTextTxtDaObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDa);
		IObservableValue dayDtmDaCalculoObserveValue = PojoProperties.value("day").observe(dtmDaCalculo);
		bindingContext.bindValue(observeTextTxtDaObserveWidget, dayDtmDaCalculoObserveValue, null, null);
		//
		IObservableValue observeTextTxtMesObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtMes);
		IObservableValue monthDtmDaCalculoObserveValue = PojoProperties.value("month").observe(dtmDaCalculo);
		bindingContext.bindValue(observeTextTxtMesObserveWidget, monthDtmDaCalculoObserveValue, null, null);
		//
		IObservableValue observeTextTxtAoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtAo);
		IObservableValue yearDtmDaCalculoObserveValue = PojoProperties.value("year").observe(dtmDaCalculo);
		bindingContext.bindValue(observeTextTxtAoObserveWidget, yearDtmDaCalculoObserveValue, null, null);
		//
		return bindingContext;
	}
}
