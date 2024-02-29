import axios from "axios";
/*
const response = [
  {
    first: {
      ime: "Aleksandra",
      prezime: "Stanković",
      email: "aleksandra.stankovic@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 41,
      korisnickoIme: "aleksandra",
    },
    second: 10,
    third: 10,
  },
  {
    first: {
      ime: "Pero",
      prezime: "Grubač",
      email: "pero.grubac@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 43,
      korisnickoIme: "pero",
    },
    second: 11,
    third: 10,
  },
  {
    first: {
      ime: "Pero",
      prezime: "Grubač",
      email: "pero.grubac@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 43,
      korisnickoIme: "stefan",
    },
    second: 10,
    third: 1,
  },
  {
    first: {
      ime: "Aleksandra",
      prezime: "Stanković",
      email: "aleksandra.stankovic@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 41,
      korisnickoIme: "drljaca",
    },
    second: 10,
    third: 10,
  },
  {
    first: {
      ime: "Pero",
      prezime: "Grubač",
      email: "pero.grubac@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 43,
      korisnickoIme: "vaso",
    },
    second: 11,
    third: 10,
  },
  {
    first: {
      ime: "Pero",
      prezime: "Grubač",
      email: "pero.grubac@student.etf.unibl.org",
      uloga: "Korisnik",
      idKorisnika: 43,
      korisnickoIme: "sofija",
    },
    second: 10,
    third: 1,
  },
];
*/

const getData = async (godina) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/stats/taskbymonth/${godina}`, // Use template literals to insert the variable
      {
        headers: {
          "Content-Type": "application/json",
          Authorization:
            "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGVrc2FuZHJhIiwiaWF0IjoxNzA5MTU0MDI3LCJleHAiOjE3MDkxNTU4Mjd9.y_cKC_7XhV3Q9h7phXg-E8bLdBWgdV_golh-HKnaP0E",
        },
      }
    );

    // Map the data after getting the response
    const mappedData = response.data.map((item) => ({
      mjesec: item.second,
      list: [
        {
          korisnickoIme: item.first.korisnickoIme,
          brojZadataka: item.third,
        },
      ],
    }));
    return mappedData;
  } catch (error) {
    console.error(error);
  }
};

export default getData;
