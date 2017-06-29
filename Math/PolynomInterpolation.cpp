/* Polynom Interpolation
 ** ?
 */
//START
// Alias für ein Polynom: Stützstellen und Koeffizienten.
typedef pair<vector<Number>, vector<Fraction>> Polynom;

// Interpoliert für die angegebenen nach x-Wert sortierten Stützpunkte ein Polynom.
Polynom NewtonInterpolation(vector<pair<Number, Number>> samples)
{
	// Das Polynom hat maximal einen Grad eins kleiner als die Anzahl der Stützstellen
	int n = samples.size() - 1;

	// Polynom durch Lösen des Newton-Gleichungssystems bestimmen
	Polynom result;
	for(int i = 0; i <= n; ++i)
	{
		// Koeffizienten berechnen
		Fraction a(samples[i].second, 1);
		for(int j = 0; j < i; ++j)
		{
			a -= result.second[j];
			a *= Fraction(1, samples[i].first - samples[j].first);
		}

		// Stützstelle und Koeffizienten merken
		result.first.push_back(samples[i].first);
		result.second.push_back(a);
	}

	// Fertig
	return result;
}

// Berechnet den Wert des Polynoms an der gegebenen Stelle.
Fraction CalculateValue(Polynom p, Number x)
{
	// Grad für kürzere Schreibweise
	int n = p.first.size() - 1;

	// Mit Horner-Schema Wert berechnen
	Fraction b = p.second[n];
	for(int i = n - 1; i >= 0; --i)
	{
		b *= Fraction(x - p.first[i], 1);
		b += p.second[i];
	}
	return b;
}
//END
