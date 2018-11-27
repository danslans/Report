package com.darckcode.reports.activity;
import android.app.*;
import android.os.*;
import com.darckcode.reports.*;
import android.widget.*;
import android.view.*;
import android.graphics.*;
import android.widget.AdapterView.*;
import com.darckcode.reports.enums.*;
import com.darckcode.reports.services.*;
import com.darckcode.reports.model.*;
import org.apache.http.conn.ssl.*;
import com.darckcode.reports.adapters.*;
import java.util.*;
import android.view.ContextMenu.*;

public class FormReport extends Activity
{
	private Spinner spinnerMeta;
	private Spinner spinnerDiferir;
	private EditText valorTotal;
	private EditText nombreReporte;
	ArrayAdapter<String> adapterMeta ;
	ArrayAdapter<String> adapterdDferir ;
	Dialog diag;
	private int btnPasado=0;
	private int btnSelectedSemanal=0;
	private String metaSelected="";
	String nameBtnSelected ="";
	String nameBtnSelectedSemanal="";
	String[] diferirList ;
	String[] metas;
	private ReportService reportService;
	private ListView listReports;
	private ReportAdapter reportAdapter;
	private ArrayList<Report> reports;
	
	
	@Override
	protected void onCreate ( Bundle savedInstanceState )
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_create_report);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		reportService =new ReportService(getApplicationContext());
		reports = reportService.getAllReports();
		spinnerMeta = (Spinner) findViewById(R.id.spMeta);
		spinnerDiferir = (Spinner) findViewById(R.id.spDiferirEn);
		valorTotal = (EditText) findViewById(R.id.eTextValorTotal);
		nombreReporte = (EditText) findViewById(R.id.eTextNameReport);
		listReports = (ListView) findViewById(R.id.listReports);
		reportAdapter = new ReportAdapter(reports,getApplicationContext());
		listReports.setAdapter(reportAdapter);
		registerForContextMenu(listReports);
		metas = new String[] {"","Anual","Semestral","Trimestral"};
		diferirList = new String[] {"","mes","semanas"};
		adapterMeta = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, metas);
		adapterdDferir = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diferirList);
		spinnerMeta.setAdapter(adapterMeta);
		spinnerDiferir.setAdapter(adapterdDferir);
		spinnerDiferir.setEnabled(true);
		spinnerDiferir.setActivated(true);
		spinnerDiferir.setClickable(true);
		spinnerDiferir.setClipChildren(true);
		spinnerDiferir.setContextClickable(true);
		spinnerDiferir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
				@Override
				public void onItemSelected ( AdapterView av, View v, int i, long l )
				{
					limpiarValores();
					switch (diferirList [i])
					{
						case "mes":
							diag = showDiag(R.layout.dialog_diferir_mes, "Numero del dia");
							diag.show();
							break;
						case "semanas":
							diag = showDiag(R.layout.dialog_diferir_semana, "Dia de la semana");
							diag.show();
							break;
					}
				}
				@Override
				public void onNothingSelected ( AdapterView ad )
				{
					Toast.makeText(getApplicationContext(), "entre", Toast.LENGTH_LONG).show();
				}

			});
		spinnerDiferir.setOnLongClickListener(new View.OnLongClickListener(){
				public boolean	onLongClick ( View v )
				{
					Toast.makeText(getApplicationContext(), "Se reestablece la configuracion Diferir", Toast.LENGTH_LONG).show();
					limpiarValores();
					spinnerDiferir.setSelection(0);
					return true;
				}
			});
		spinnerMeta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onNothingSelected(AdapterView a){
				
			}
			@Override
			public void onItemSelected(AdapterView a,View v,int i, long l){
				metaSelected = metas[i];
			}
		});
		
		listReports.setOnItemLongClickListener(new  AdapterView.OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView a, View v,final int i,long l){
				PopupMenu menu = new PopupMenu(getApplicationContext(),v);
				menu.getMenuInflater().inflate(R.menu.menu_report_options,menu.getMenu());
				menu.show();
				menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
					@Override
					public boolean onMenuItemClick(MenuItem m){
						switch(m.getItemId()){
							case R.id.itemEliminarReport:
								reportService.deleteReport(reports.get(i));
								reports.clear();
								reports.addAll(reportService.getAllReports());
								reportAdapter.notifyDataSetChanged();
								break;
							case R.id.itemEditarReport:
								Toast.makeText(getApplicationContext(),"editar",Toast.LENGTH_LONG).show();
								break;
						}
						return true;
					}
				});	
				return true;
			}
		});
	}
	
	private void limpiarValores ( )
	{
		btnPasado = 0;
		btnSelectedSemanal = 0;
		nameBtnSelectedSemanal = "";
		nameBtnSelected = "";
		diferirList [0] = "";
		diferirList [1] = "mes";
		diferirList [2] = "semanas";
		adapterdDferir.notifyDataSetChanged();
	}

	private Dialog showDiag ( int layout, String title )
	{
		Dialog dialog = new Dialog(FormReport.this);
		dialog.setContentView(layout);
		dialog.setTitle(title);
		dialog.setCancelable(false);
		return dialog;
	}

	public void selectNumDia ( View v )
	{
		Button btnSelected= (Button) v;
		btnSelected.setTextColor(Color.WHITE);
		btnSelected.setBackgroundColor(Color.BLACK);
		if (btnSelected.getText().toString().matches("\\d+"))
		{
			nameBtnSelected = btnSelected.getText().toString();
		}
		switch (v.getId())
		{
			case R.id.btnAceptarMes:
				if (btnPasado > 0)
				{
					diag.hide();
					diferirList [1] = "mes: todos los " + nameBtnSelected;
					adapterdDferir.notifyDataSetChanged();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Debe seleccionar un día", Toast.LENGTH_LONG).show();
				}
				return;
		}
		resetColorsMensual(v);
	}

	private void resetColorsMensual ( View v )
	{
		if (btnPasado != 0)
		{
			Button btnPas =(Button) diag.findViewById(btnPasado);
			btnPas.setBackgroundColor(Color.parseColor("#cccccc"));
			btnPas.setTextColor(Color.BLACK);
			btnPasado = v.getId();
		}
		else
		{
			btnPasado = v.getId();
		}
		Toast.makeText(getApplicationContext(), "Ha seleccionado todos los " + nameBtnSelected + " del mes", Toast.LENGTH_SHORT).show();
	}

	public void selectDiaSemanal ( View v )
	{
		Button btnSelected= (Button) v;
		btnSelected.setTextColor(Color.WHITE);
		btnSelected.setBackgroundColor(Color.BLACK);
		switch (v.getId())
		{
			case R.id.btnAceptarSemana:
				if (btnSelectedSemanal > 0)
				{
					diag.hide();
					diferirList [2] = "semanas: todos los " + nameBtnSelectedSemanal;
					adapterdDferir.notifyDataSetChanged();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Debe seleccionar un día", Toast.LENGTH_LONG).show();
				}
				break;
			default:
				nameBtnSelectedSemanal = EnumDias.getDia(btnSelected.getText().toString());
				resetColorsSemanal(v);
				break;
		}
	}

	private void resetColorsSemanal ( View v )
	{
		if (btnSelectedSemanal != 0)
		{
			Button btnPas =(Button) diag.findViewById(btnSelectedSemanal);
			btnPas.setBackgroundColor(Color.parseColor("#cccccc"));
			btnPas.setTextColor(Color.BLACK);
			btnSelectedSemanal = v.getId();
		}
		else
		{
			btnSelectedSemanal = v.getId();
		}
		Toast.makeText(getApplicationContext(), "Ha seleccionado todos los " + nameBtnSelectedSemanal, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu ( Menu menu )
	{
		getMenuInflater().inflate(R.menu.menu_form_report, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected ( int featureId, MenuItem item )
	{

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected ( MenuItem item )
	{
		switch (item.getItemId())
		{
			case R.id.itemGuardar:
				if(validateReport()){
					Report report = new Report();
					report.setName(nombreReporte.getText().toString());
					report.setValue(valorTotal.getText().toString());
					report.setTypeReport(metaSelected);
					report.setDiffer(nameBtnSelected.isEmpty()?nameBtnSelectedSemanal:nameBtnSelected);
					reportService.saveReport(report);
					reports.clear();
					reports.addAll(reportService.getAllReports());
					reportAdapter.notifyDataSetChanged();
					//Toast.makeText(getApplicationContext(),report.toString(),Toast.LENGTH_LONG).show();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private Boolean validateReport ( )
	{
		
		if (nombreReporte.getText().toString().isEmpty() 
			|| valorTotal.getText().toString().isEmpty()
			|| metaSelected.isEmpty()
			|| (btnPasado==0 && btnSelectedSemanal==0)
			)
		{
			Toast.makeText(getApplicationContext(),"Todos los campos son obligatorios.",Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}


}
