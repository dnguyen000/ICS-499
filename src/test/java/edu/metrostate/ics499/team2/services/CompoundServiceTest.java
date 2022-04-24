package edu.metrostate.ics499.team2.services;

import static edu.metrostate.ics499.team2.constants.PugApiConstants.PUG_INPUT;
import static edu.metrostate.ics499.team2.constants.PugApiConstants.PUG_OPERATION;
import static edu.metrostate.ics499.team2.constants.PugApiConstants.PUG_OUTPUT;
import static edu.metrostate.ics499.team2.constants.PugApiConstants.PUG_PROLOG;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.metrostate.ics499.team2.exceptions.domain.PugApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.PugApiDTO;
import edu.metrostate.ics499.team2.repositories.CompoundRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompoundServiceTest {

	@Autowired
	private CompoundService compoundService;

	@MockBean
	private RestTemplate restMock;

	private CompoundRepository repoMock;

	@MockBean
	private QuizService quizMock;

	@LocalServerPort
	int randomServerPort;

	String compound = "NaCl";
	String responseBody = "{\n"
			+ "    \"PropertyTable\": {\n"
			+ "        \"Properties\": [\n"
			+ "            {\n"
			+ "                \"CID\": 5234,\n"
			+ "                \"MolecularFormula\": \"ClNa\",\n"
			+ "                \"MolecularWeight\": \"58.44\",\n"
			+ "                \"Title\": \"Sodium chloride\"\n"
			+ "            },\n"
			+ "            {\n"
			+ "                \"CID\": 23667643,\n"
			+ "                \"MolecularFormula\": \"ClNa\",\n"
			+ "                \"MolecularWeight\": \"57.45\",\n"
			+ "                \"Title\": \"Sodium chloride na-22\"\n"
			+ "            }\n"
			+ "        ]\n"
			+ "    }\n"
			+ "}";

	@BeforeEach
	public void setUp() {
		repoMock = mock(CompoundRepository.class);
		ReflectionTestUtils.setField(compoundService, "compoundRepo", repoMock);
		ReflectionTestUtils.setField(compoundService, "restTemplate", restMock);
		ReflectionTestUtils.setField(compoundService, "quizService", quizMock);
	}

	@Test
	@DisplayName("It should return false if the compound does not exist in the repo")
	void doesValueExistInRepo_false() {
		List<Compound> mockValue = new ArrayList<>();
		Mockito.doReturn(mockValue).when(repoMock).findCompoundByFormula("H2O");

		assertFalse(compoundService.doesValueExistInRepo("H2O"));
	}

	@Test
	@DisplayName("It should return true if the compound exists in the repo")
	void doesValueExistInRepo_true() {
		String userId = "12345";
		HashMap<String, Integer> elements = new HashMap<>();
		elements.put("H", 2);
		elements.put("O", 1);

		Compound c1 = new Compound(elements, userId);
		List<Compound> mockValue = new ArrayList<>();
		mockValue.add(c1);
		Mockito.doReturn(mockValue).when(repoMock).findCompoundByFormula("H2O");

		assertTrue(compoundService.doesValueExistInRepo("H2O"));
	}

	@Test
	@DisplayName("It should return the value from the repo if it exists")
	void validateInput_returnFromRepo() throws PugApiException {
		String userId = "12345";
		HashMap<String, Integer> elements = new HashMap<>();
		elements.put("H", 2);
		elements.put("O", 1);

		Compound c1 = new Compound(elements, userId);
		String formula = "H2O";
		List<Compound> mockValue = new ArrayList<>();
		mockValue.add(c1);
		Mockito.doReturn(mockValue).when(repoMock).findCompoundByFormula(formula);

		assertNotNull(compoundService.validateInput(c1));

		verify(restMock, never()).getForObject(PUG_PROLOG + PUG_INPUT + formula + PUG_OPERATION + PUG_OUTPUT, HashMap.class);
	}

	@Test
	@DisplayName("It should return the value from PUG API if the value does not exist in the repo")
	void validateInput_returnFromPugApi() throws Exception {
		List<Compound> mockValue = new ArrayList<>();
		String formula = "NaCl";
		String userId = "12345";
		HashMap<String, Integer> elements = new HashMap<>();
		elements.put("Na", 1);
		elements.put("Cl", 1);
		Mockito.doReturn(mockValue).when(repoMock).findCompoundByFormula("NaCl");
		Compound c1 = new Compound(elements, userId);
		Mockito.doReturn(c1).when(repoMock).save(c1);

		PugApiDTO pugApiMock = new PugApiDTO();
		pugApiMock.initializePropertyTableObj();
		pugApiMock.appendToPropertyTableObj(5234, "ClNa", "58.44", "Sodium chloride");
		Mockito.doReturn(pugApiMock).when(restMock).getForObject(PUG_PROLOG + PUG_INPUT + compound + PUG_OPERATION + PUG_OUTPUT, PugApiDTO.class);

		compoundService.validateInput(c1);

		verify(restMock, times(1)).getForObject(PUG_PROLOG + PUG_INPUT + formula + PUG_OPERATION + PUG_OUTPUT, PugApiDTO.class);
		verify(repoMock, times(1)).save(c1);
		verify(quizMock, times(1)).createCompoundQuizes(c1, c1.getUserId(), "compound");
		verify(quizMock, times(1)).createElementQuizes(c1, c1.getUserId(), "element");
	}
}
