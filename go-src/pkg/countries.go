package covid19

type Countries struct {
	Data []struct {
		Location  string `json:"location"`
		Confirmed int    `json:"confirmed"`
		Deaths    int    `json:"deaths"`
		Recovered int    `json:"recovered"`
		Active    int    `json:"active"`
	} `json:"data"`
	Dt string  `json:"dt"`
	Ts float64 `json:"ts"`
}

func GetCountries() []byte {
	body, err := sendRequest("/v2/current")

	if err != nil {
		return nil
	}
	return body
}
