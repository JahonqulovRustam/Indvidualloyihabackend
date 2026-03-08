package com.medicore.hms.service;

import com.medicore.hms.dto.RecommendationResponse;
import com.medicore.hms.dto.RecommendationResponse.MedicationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

        public List<String> getSupportedConditions() {
                return List.of(
                                "Viral Influenza (Flu / Common Cold)",
                                "Tension Headache / Migraine",
                                "Type 2 Diabetes Mellitus",
                                "Arterial Hypertension",
                                "Allergic Reaction",
                                "Acute Gastritis",
                                "Acute Bronchitis (Cough)",
                                "Bacterial Conjunctivitis",
                                "Urinary Tract Infection (UTI)",
                                "Iron Deficiency Anemia",
                                "Osteoarthritis",
                                "Gouty Arthritis",
                                "Vitamin D Deficiency",
                                "Tonsillitis (Sore Throat)",
                                "Otitis Media (Ear Infection)",
                                "Insomnia (Sleep Disorder)",
                                "GERD (Acid Reflux)",
                                "Bronchial Asthma",
                                "Atopic Dermatitis (Eczema)",
                                "Bacterial Sinusitis");
        }

        public RecommendationResponse getRecommendations(String diagnosis) {
                String lowerDiag = diagnosis.toLowerCase();

                if (lowerDiag.contains("flu") || lowerDiag.contains("gripp") || lowerDiag.contains("cold")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Viral Influenza (Flu / Common Cold)")
                                        .generalAdvice("The patient is advised to drink plenty of fluids, follow bed rest, and eat warm meals.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Paracetamol").dosage("500mg")
                                                                        .frequency("1 tablet 3 times a day (after meals)")
                                                                        .duration("3-5 days").build(),
                                                        MedicationDto.builder().name("Ibuprofen").dosage("400mg")
                                                                        .frequency("As needed (if fever is high)")
                                                                        .duration("3 days").build(),
                                                        MedicationDto.builder().name("Vitamin C").dosage("1000mg")
                                                                        .frequency("1 tablet once a day")
                                                                        .duration("10 days").build()))
                                        .build();
                } else if (lowerDiag.contains("headache") || lowerDiag.contains("bosh og'rig'i")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Tension Headache / Migraine")
                                        .generalAdvice("Rest in a quiet and dark room is recommended. Blood pressure should be monitored.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Ibuprofen").dosage("400mg")
                                                                        .frequency("Once, when the headache occurs")
                                                                        .duration("As needed").build(),
                                                        MedicationDto.builder().name("Citramon").dosage("1 tab")
                                                                        .frequency("1-2 times a day")
                                                                        .duration("Not more than 3 days").build()))
                                        .build();
                } else if (lowerDiag.contains("diabetes") || lowerDiag.contains("diabet")
                                || lowerDiag.contains("qandli")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Type 2 Diabetes Mellitus")
                                        .generalAdvice("Strict adherence to a diet (reducing sugar and floury foods). Regular monitoring of blood sugar levels.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Metformin").dosage("500mg")
                                                                        .frequency("1 tablet 2 times a day (with meals)")
                                                                        .duration("Ongoing").build(),
                                                        MedicationDto.builder().name("Gliclazide").dosage("30mg")
                                                                        .frequency("Once in the morning (on an empty stomach)")
                                                                        .duration("Ongoing").build()))
                                        .build();
                } else if (lowerDiag.contains("pressure") || lowerDiag.contains("davleniya")
                                || lowerDiag.contains("hypertension")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Arterial Hypertension")
                                        .generalAdvice("Limit salt and fatty foods. Light daily physical exercises.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Amlodipine").dosage("5mg")
                                                                        .frequency("Once a day").duration("Ongoing")
                                                                        .build(),
                                                        MedicationDto.builder().name("Lisinopril").dosage("10mg")
                                                                        .frequency("Once a day").duration("Ongoing")
                                                                        .build()))
                                        .build();
                } else if (lowerDiag.contains("gastritis") || lowerDiag.contains("oshqozon")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Acute Gastritis")
                                        .generalAdvice("Avoid spicy, acidic, and fried foods. Eat small, frequent meals.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Omeprazole").dosage("20mg")
                                                                        .frequency("Once a day (30 min before breakfast)")
                                                                        .duration("14 days").build(),
                                                        MedicationDto.builder().name("Almagel").dosage("10ml")
                                                                        .frequency("3 times a day (after meals)")
                                                                        .duration("7 days").build()))
                                        .build();
                } else if (lowerDiag.contains("bronchitis") || lowerDiag.contains("bronxit")
                                || lowerDiag.contains("cough")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Acute Bronchitis (Cough)")
                                        .generalAdvice("Stay hydrated. Use a humidifier if possible. Avoid smoking and pollutants.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Ambroxol").dosage("30mg")
                                                                        .frequency("1 tablet 3 times a day")
                                                                        .duration("5-7 days").build(),
                                                        MedicationDto.builder().name("Acetylcysteine (ACC)")
                                                                        .dosage("600mg").frequency("Once a day")
                                                                        .duration("5 days").build()))
                                        .build();
                } else if (lowerDiag.contains("conjunctivitis") || lowerDiag.contains("ko'z")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Bacterial Conjunctivitis")
                                        .generalAdvice("Avoid touching or rubbing eyes. Use a separate towel. Wash hands frequently.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Levofloxacin Eye Drops")
                                                                        .dosage("0.5%")
                                                                        .frequency("1-2 drops 4 times a day")
                                                                        .duration("7 days").build(),
                                                        MedicationDto.builder().name("Ophthalmo-Septonex").dosage("-")
                                                                        .frequency("3 times a day").duration("5 days")
                                                                        .build()))
                                        .build();
                } else if (lowerDiag.contains("uti") || lowerDiag.contains("urinary") || lowerDiag.contains("siydik")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Urinary Tract Infection (UTI)")
                                        .generalAdvice("Drink plenty of water. Avoid caffeine and alcohol. Maintain good personal hygiene.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Ciprofloxacin").dosage("500mg")
                                                                        .frequency("1 tablet 2 times a day")
                                                                        .duration("5-7 days").build(),
                                                        MedicationDto.builder().name("Canephron N").dosage("2 tabs")
                                                                        .frequency("3 times a day").duration("14 days")
                                                                        .build()))
                                        .build();
                } else if (lowerDiag.contains("anemia") || lowerDiag.contains("iron")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Iron Deficiency Anemia")
                                        .generalAdvice("Eat iron-rich foods (red meat, spinach, legumes). Take with Vitamin C for better absorption.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Ferrous Sulfate").dosage("200mg")
                                                                        .frequency("1 tablet twice a day")
                                                                        .duration("3-6 months").build(),
                                                        MedicationDto.builder().name("Folic Acid").dosage("5mg")
                                                                        .frequency("Once a day").duration("1 month")
                                                                        .build()))
                                        .build();
                } else if (lowerDiag.contains("osteoarthritis") || lowerDiag.contains("bo'g'im")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Osteoarthritis")
                                        .generalAdvice("Maintain a healthy weight. Low-impact exercises (swimming, cycling).")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Glucosamine & Chondroitin")
                                                                        .dosage("-").frequency("1 tab 2 times a day")
                                                                        .duration("2-3 months").build(),
                                                        MedicationDto.builder().name("Diclofenac Gel").dosage("-")
                                                                        .frequency("Apply to joint 3 times a day")
                                                                        .duration("10 days").build()))
                                        .build();
                } else if (lowerDiag.contains("gout") || lowerDiag.contains("podagra")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Gouty Arthritis")
                                        .generalAdvice("Limit high-purine foods (red meat, seafood, alcohol). Drink plenty of water.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Allopurinol").dosage("100mg")
                                                                        .frequency("Once a day").duration("Long-term")
                                                                        .build(),
                                                        MedicationDto.builder().name("Colchicine").dosage("0.5mg")
                                                                        .frequency("1 tablet 2 times a day (during flare)")
                                                                        .duration("3 days").build()))
                                        .build();
                } else if (lowerDiag.contains("vitamin d") || lowerDiag.contains("rahit")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Vitamin D Deficiency")
                                        .generalAdvice("Get safe sun exposure. Eat fatty fish and fortified dairy products.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Cholecalciferol (D3)")
                                                                        .dosage("5000 IU").frequency("Once a week")
                                                                        .duration("8-12 weeks").build()))
                                        .build();
                } else if (lowerDiag.contains("tonsillitis") || lowerDiag.contains("angina")
                                || lowerDiag.contains("throat")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Tonsillitis (Sore Throat)")
                                        .generalAdvice("Gargle with warm salt water. Drink warm soothing liquids. Rest your voice.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Amoxicillin").dosage("500mg")
                                                                        .frequency("1 tablet 3 times a day")
                                                                        .duration("10 days").build(),
                                                        MedicationDto.builder().name("Furacilin (Gargle)").dosage("-")
                                                                        .frequency("4-5 times a day").duration("7 days")
                                                                        .build()))
                                        .build();
                } else if (lowerDiag.contains("otitis") || lowerDiag.contains("quloq")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Otitis Media (Ear Infection)")
                                        .generalAdvice("Keep the ear dry. Avoid inserting cotton swabs.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Otipax Ear Drops").dosage("-")
                                                                        .frequency("4 drops 3 times a day")
                                                                        .duration("7 days").build(),
                                                        MedicationDto.builder().name("Amoxicillin-Clavulanate")
                                                                        .dosage("875/125mg").frequency("Once daily")
                                                                        .duration("7 days").build()))
                                        .build();
                } else if (lowerDiag.contains("insomnia") || lowerDiag.contains("uyqusiz")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Insomnia (Sleep Disorder)")
                                        .generalAdvice("Establish a regular sleep schedule. Avoid screens 1 hour before bed. Limit caffeine.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Melatonin").dosage("3mg")
                                                                        .frequency("30 min before bed")
                                                                        .duration("14 days").build(),
                                                        MedicationDto.builder().name("Valerian Extract").dosage("-")
                                                                        .frequency("1 tab at evening")
                                                                        .duration("1 month").build()))
                                        .build();
                } else if (lowerDiag.contains("gerd") || lowerDiag.contains("reflux") || lowerDiag.contains("zarda")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("GERD (Acid Reflux)")
                                        .generalAdvice("Eat smaller meals. Do not lie down within 3 hours of eating. Elevate head of bed.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Pantoprazole").dosage("40mg")
                                                                        .frequency("Once a day before breakfast")
                                                                        .duration("28 days").build(),
                                                        MedicationDto.builder().name("Gaviscon Liquid").dosage("10ml")
                                                                        .frequency("After meals and at bedtime")
                                                                        .duration("As needed").build()))
                                        .build();
                } else if (lowerDiag.contains("asthma") || lowerDiag.contains("astma")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Bronchial Asthma")
                                        .generalAdvice("Identify and avoid triggers (pollen, dust, smoke). Always keep emergency inhaler.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Salbutamol Inhaler")
                                                                        .dosage("100mcg")
                                                                        .frequency("2 puffs as needed for shortness of breath")
                                                                        .duration("As needed").build(),
                                                        MedicationDto.builder().name("Budesonide Inhaler")
                                                                        .dosage("200mcg")
                                                                        .frequency("1 puff twice a day")
                                                                        .duration("Long-term").build()))
                                        .build();
                } else if (lowerDiag.contains("eczema") || lowerDiag.contains("dermatitis")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Atopic Dermatitis (Eczema)")
                                        .generalAdvice("Moisturize skin frequently. Use fragrance-free soaps. Avoid hot showers.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Hydrocortisone Cream")
                                                                        .dosage("1%")
                                                                        .frequency("Apply to affected area 2 times a day")
                                                                        .duration("7 days").build(),
                                                        MedicationDto.builder().name("Emollient Cream").dosage("-")
                                                                        .frequency("Multiple times a day")
                                                                        .duration("Ongoing").build()))
                                        .build();
                } else if (lowerDiag.contains("sinusitis") || lowerDiag.contains("gaymorit")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Bacterial Sinusitis")
                                        .generalAdvice("Use saline nasal rinses. Inhale steam. Apply warm compress to face.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Xylometazoline Spray").dosage("-")
                                                                        .frequency("1 spray each nostril 2 times a day")
                                                                        .duration("Not more than 5 days").build(),
                                                        MedicationDto.builder().name("Amoxicillin").dosage("500mg")
                                                                        .frequency("1 tablet 3 times a day")
                                                                        .duration("10 days").build()))
                                        .build();
                } else if (lowerDiag.contains("allergy") || lowerDiag.contains("allergiya")) {
                        return RecommendationResponse.builder()
                                        .diagnosis("Allergic Reaction")
                                        .generalAdvice("Stay away from allergens (dust, smell, food).")
                                        .medications(List.of(
                                                        MedicationDto.builder().name("Cetirizine / Loratadine")
                                                                        .dosage("10mg")
                                                                        .frequency("Once a day (at evening)")
                                                                        .duration("5-7 days").build(),
                                                        MedicationDto.builder().name("Dexamethasone")
                                                                        .dosage("As required")
                                                                        .frequency("Only in severe cases")
                                                                        .duration("Under doctor's supervision")
                                                                        .build()))
                                        .build();
                } else {
                        return RecommendationResponse.builder()
                                        .diagnosis("Unspecified Condition / Other")
                                        .generalAdvice("Symptomatic treatment. Additional diagnostic tests (blood test, ultrasound) are recommended.")
                                        .medications(List.of(
                                                        MedicationDto.builder().name(
                                                                        "Individualized based on specialist consultation")
                                                                        .dosage("-").frequency("-").duration("-")
                                                                        .build()))
                                        .build();
                }
        }
}
