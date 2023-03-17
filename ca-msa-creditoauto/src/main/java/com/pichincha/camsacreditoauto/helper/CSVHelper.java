package com.pichincha.camsacreditoauto.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;
import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;
import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;
import com.pichincha.camsacreditoauto.repository.service.ClienteRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.EjecutivoRepositoryService;
import com.pichincha.camsacreditoauto.service.MarcaRepositoryService;

@Component
public class CSVHelper implements ApplicationRunner {

	@Autowired
	private MarcaRepositoryService marcaRepositoryService;

	@Autowired
	private ClienteRepositoryService clienteRepositoryService;

	@Autowired
	private EjecutivoRepositoryService ejecutivoRepositoryService;
	
	@Value("${pichincha.camsacreditoauto.ruta.base.proyecto}")
	private String rutaBasePoyecto;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			InputStream marcasCsvStream = new FileInputStream(new File(
					rutaBasePoyecto + "\\ca-msa-creditoauto\\src\\main\\java\\com\\pichincha\\camsacreditoauto\\util\\marcas.csv"));
			InputStream ejecutivosCsvStream = new FileInputStream(new File(
					rutaBasePoyecto + "\\ca-msa-creditoauto\\src\\main\\java\\com\\pichincha\\camsacreditoauto\\util\\ejecutivos.csv"));
			InputStream clientesCsvStream = new FileInputStream(new File(
					rutaBasePoyecto + "\\ca-msa-creditoauto\\src\\main\\java\\com\\pichincha\\camsacreditoauto\\util\\clientes.csv"));
			this.guardarDataCsv(marcasCsvStream, ejecutivosCsvStream, clientesCsvStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void guardarDataCsv(InputStream csvMarcas, InputStream csvEjecutivos, InputStream csvClientes) {
		try (BufferedReader fileReaderMarcas = new BufferedReader(new InputStreamReader(csvMarcas, "UTF-8"));
				CSVParser csvParserMarcas = new CSVParser(fileReaderMarcas,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

				BufferedReader fileReaderEjecutivos = new BufferedReader(new InputStreamReader(csvEjecutivos, "UTF-8"));
				CSVParser csvParserEjecutivos = new CSVParser(fileReaderEjecutivos,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

				BufferedReader fileReaderClientes = new BufferedReader(new InputStreamReader(csvClientes, "UTF-8"));
				CSVParser csvParserClientes = new CSVParser(fileReaderClientes,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			// Marcas
			this.guardarDataMarcasCsv(csvParserMarcas);

			// Ejecutivos
			this.guardarDataEjecutivosCsv(csvParserEjecutivos);

			// Clientes
			this.guardarDataClientesCsv(csvParserClientes);

		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	

	
	private void guardarDataMarcasCsv(CSVParser csvParserMarcas) throws IOException {
		List<MarcaEntity> marcaEntityList = new ArrayList<>();
		Iterable<CSVRecord> csvRecordsMarcas = csvParserMarcas.getRecords();
		for (CSVRecord csvRecordMarca : csvRecordsMarcas) {
			MarcaEntity marcaEntity = new MarcaEntity();
			marcaEntity.setId(Integer.valueOf(csvRecordMarca.get("id")));
			marcaEntity.setMarca(csvRecordMarca.get("marca"));
			marcaEntityList.add(marcaEntity);
		}
		marcaRepositoryService.guardarListaMarcas(marcaEntityList);
	}
	
	private void guardarDataEjecutivosCsv(CSVParser csvParserEjecutivos) throws IOException {
		List<EjecutivoEntity> ejecutivoEntityList = new ArrayList<>();
		Iterable<CSVRecord> csvRecordsEjecutivos = csvParserEjecutivos.getRecords();
		for (CSVRecord csvRecordEjecutivo : csvRecordsEjecutivos) {
			EjecutivoEntity ejecutivoEntity = new EjecutivoEntity();
			ejecutivoEntity.setId(Integer.valueOf(csvRecordEjecutivo.get("id")));
			ejecutivoEntity.setIdentificacion(csvRecordEjecutivo.get("identificacion"));
			ejecutivoEntity.setNombres(csvRecordEjecutivo.get("nombres"));
			ejecutivoEntity.setApellidos(csvRecordEjecutivo.get("apellidos"));
			ejecutivoEntity.setDireccion(csvRecordEjecutivo.get("direccion"));
			ejecutivoEntity.setTelefono(csvRecordEjecutivo.get("telefono"));
			ejecutivoEntity.setCelular(csvRecordEjecutivo.get("celular"));
			ejecutivoEntity.setPatio(Integer.valueOf(csvRecordEjecutivo.get("patio")));
			ejecutivoEntity.setEdad(Integer.valueOf(csvRecordEjecutivo.get("edad")));
			ejecutivoEntityList.add(ejecutivoEntity);
		}
		ejecutivoRepositoryService.guardarListaEjecutivos(ejecutivoEntityList);
	}
	
	private void guardarDataClientesCsv(CSVParser csvParserClientes) throws IOException {
		List<ClienteEntity> clienteEntityList = new ArrayList<>();
		Iterable<CSVRecord> csvRecordsClientes = csvParserClientes.getRecords();
		for (CSVRecord csvRecordCliente : csvRecordsClientes) {
			ClienteEntity clienteEntity = new ClienteEntity();
			clienteEntity.setId(Integer.valueOf(csvRecordCliente.get("id")));
			clienteEntity.setIdentificacion(csvRecordCliente.get("identificacion"));
			clienteEntity.setNombres(csvRecordCliente.get("nombres"));
			clienteEntity.setApellidos(csvRecordCliente.get("apellidos"));
			clienteEntity.setTelefono(csvRecordCliente.get("telefono"));
			clienteEntity.setEdad(Integer.valueOf(csvRecordCliente.get("edad")));
			clienteEntity.setFechaNacimiento(LocalDate.parse(csvRecordCliente.get("fecha_nacimiento")));
			clienteEntity.setEstadoCivil(csvRecordCliente.get("estado_civil"));
			clienteEntity.setIdentificacionConyugue(csvRecordCliente.get("identificacion_conyugue"));
			clienteEntity.setNombreConyugue(csvRecordCliente.get("nombre_conyugue"));
			clienteEntity.setSujetoCredito(Boolean.valueOf(csvRecordCliente.get("sujeto_credito")));
			clienteEntityList.add(clienteEntity);
		}
		clienteRepositoryService.guardarListaClientes(clienteEntityList);
	}

}
