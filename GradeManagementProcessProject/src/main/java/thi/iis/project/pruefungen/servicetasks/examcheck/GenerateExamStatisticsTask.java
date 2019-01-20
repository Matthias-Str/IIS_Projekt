package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.servicetasks.ValueIdentifiers;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

/**
 * 
 * @author matthias strauß
 *
 */

public class GenerateExamStatisticsTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        String examName = (String) execution.getVariable("examName");
        
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        Collection<StudentExam> studentexams = Arrays.asList(seWS.selectAll()).stream().filter(exam -> exam.getParticipated() && exam.getExamId().getExamId().equals(examName)).collect(Collectors.toList());
    
        if(studentexams == null){
            System.out.println("Could not find collection");
        }
        
        List<Double> grades = studentexams.stream().filter(exam -> exam.getParticipated() && exam.getGrade() != null).map(exam -> exam.getGrade().doubleValue()).collect(Collectors.toList());
        
        System.out.println(grades);
        
        double mean = grades.stream().mapToDouble(Double::doubleValue).sum() / Math.max(1, grades.size());
        List<Double> modes = getModes(grades);
        
        double mode_max = modes.stream().max(Double::compare).orElse(-1d);
        double mode_min = modes.stream().min(Double::compare).orElse(-1d);
        
        execution.setVariable("mean", mean);
        execution.setVariable("mode_max", mode_max);
        execution.setVariable("mode_min", mode_min);
    }
    
    //Quelle: https://stackoverflow.com/a/4191729
    public static List<Double> getModes(final List<Double> numbers) {
        final Map<Double, Long> countFrequencies = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final long maxFrequency = countFrequencies.values().stream()
                .mapToLong(count -> count)
                .max().orElse(-1);

        return countFrequencies.entrySet().stream()
                .filter(tuple -> tuple.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
