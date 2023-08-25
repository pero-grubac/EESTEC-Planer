package com.eestec.P.eestecP.SuperUser;
import com.eestec.P.eestecP.Koordinator.KoordinatorDAO;
import com.eestec.P.eestecP.Koordinator.KoordinatorDTO;
import com.eestec.P.eestecP.Service.KoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
    public class KoordinatorServiceImpl implements KoordinatorService {

        final private KoordinatorDAO koordinatorDAO;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        public KoordinatorServiceImpl(KoordinatorDAO koordinatorDAO) {
            this.koordinatorDAO = koordinatorDAO;
        }

        @Override
        public List<KoordinatorDTO> getAllKoordinatori() {
            List<KoordinatorDTO> koordinatoriDTOList = koordinatorDAO.findAll();
            return koordinatoriDTOList;
        }

    @Override
    @Transactional
    public KoordinatorDTO getKoordinator(Integer id) {
        KoordinatorDTO koodinatorDTO = koordinatorDAO.findById(id).orElse(null);
        return koodinatorDTO;
    }
        @Override
        @Transactional
        public KoordinatorDTO createKoordinator(KoordinatorDTO koordinatorDTO) {
            String hash = passwordEncoder.encode(koordinatorDTO.getLozinka());
            koordinatorDTO.setLozinka(hash);
            if (koordinatorDAO.save(koordinatorDTO) != null)
                return koordinatorDTO;
            else return null;
        }

        @Override
        @Transactional
        public KoordinatorDTO updateKoordinator(KoordinatorDTO koordinatorDTO) {
            if (koordinatorDTO != null) {
                String hash = passwordEncoder.encode(koordinatorDTO.getLozinka());
                koordinatorDTO.setLozinka(hash);
                KoordinatorDTO korisnik = koordinatorDAO.findById(koordinatorDTO.getIdKorisnika()).orElse(null);
                if (korisnik != null) {
                    korisnik.setLozinka((hash));
                    korisnik.setIme(koordinatorDTO.getIme());
                    korisnik.setEmail(koordinatorDTO.getEmail());
                    korisnik.setPrezime(koordinatorDTO.getPrezime());
                    korisnik.setKorisnickoIme(koordinatorDTO.getKorisnickoIme());
                    koordinatorDAO.save(korisnik);
                    return korisnik;
                }
                return null;
            }
            return null;
        }

        @Override
        @Transactional
        public boolean deleteKoordinator(Integer id) {
            KoordinatorDTO koordinator = koordinatorDAO.findById(id).orElse(null);
            if (koordinator != null) {
                koordinatorDAO.delete(koordinator);
                return true;
            }
            return false;
        }
    }

