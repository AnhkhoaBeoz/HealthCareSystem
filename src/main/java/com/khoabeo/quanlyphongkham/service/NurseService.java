        package com.khoabeo.quanlyphongkham.service;

        import com.khoabeo.quanlyphongkham.dto.DoctorDTO;
        import com.khoabeo.quanlyphongkham.dto.NurseDTO;

        import java.util.List;

        public interface NurseService {
            public List<NurseDTO> getAllNurse();

            public NurseDTO getNurseByID(long id);

            public String addNurse(NurseDTO nurseDTO);

            public String updateNurse(long id, NurseDTO nurseDTO);

            public void deleteNurse(long id);
        }
