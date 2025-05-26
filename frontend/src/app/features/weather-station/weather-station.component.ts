import {Component, OnInit} from '@angular/core';
import {Button} from 'primeng/button';
import {UIChart} from 'primeng/chart';
import {FormsModule} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {Chart, ChartConfiguration} from 'chart.js';
import zoomPlugin from 'chartjs-plugin-zoom';
import {MeasurementService} from '../../core/services/measurement/measurement.service';
import {MeasurementResponseTypes} from '../../shared/models/requests/measurement.request';
import {Select} from 'primeng/select';
import {DatePicker} from 'primeng/datepicker';
import {Measurement} from '../../shared/models/responses/measurement.response';
import {ActivatedRoute, Router} from '@angular/router';

Chart.register(zoomPlugin);

@Component({
  selector: 'app-weather-station',
  imports: [
    Button,
    UIChart,
    FormsModule,
    DatePipe,
    Select,
    DatePicker
  ],
  templateUrl: './weather-station.component.html',
  styleUrl: './weather-station.component.scss'
})
export class WeatherStationComponent implements OnInit {
  protected weatherStationId!: number;

  protected temperatureData: ChartConfiguration['data'] | undefined;
  protected humidityData: ChartConfiguration['data'] | undefined;
  protected pressureData: ChartConfiguration['data'] | undefined;
  protected co2Data: ChartConfiguration['data'] | undefined;

  protected chartOptions: ChartConfiguration['options'];

  protected selectedRange = MeasurementResponseTypes.LAST_WEEK;
  protected rangeOptions = [
    {label: 'Last week', value: MeasurementResponseTypes.LAST_WEEK},
    {label: 'Last month', value: MeasurementResponseTypes.LAST_MONTH},
    {label: 'Custom', value: MeasurementResponseTypes.CUSTOM},
  ];

  protected startDate?: Date;
  protected endDate?: Date;

  constructor(private measurementService: MeasurementService, private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.weatherStationId = +this.activatedRoute.snapshot.params['id'];

    this.getMeasurements(this.selectedRange, this.weatherStationId);
  }

  protected getMeasurements(type: MeasurementResponseTypes, stationId: number, from?: Date, to?: Date) {
    this.temperatureData = undefined;
    this.humidityData = undefined;
    this.pressureData = undefined;
    this.co2Data = undefined;

    this.measurementService.getMeasurements(type, stationId, from, to).subscribe((res) => {
      this.temperatureData = this.formatMeasurementsData(res.temperatures, 'Temperature', '°C');
      this.humidityData = this.formatMeasurementsData(res.humidity, 'Humidity', '%');
      this.pressureData = this.formatMeasurementsData(res.pressures, 'Pressure', 'hPa');
      this.co2Data = this.formatMeasurementsData(res.co2, 'CO2', 'ppm');

      this.chartOptions = this.generateChartOptions(this.temperatureData.labels as string[]);
    });
  }

  protected formatMeasurementsData(measurements: Measurement[], label: string, unit: string): ChartConfiguration['data'] {
    const labels = measurements.map(m => new Date(m.timestamp).toLocaleString());
    const values = measurements.map(m => m.value);

    return {
      labels,
      datasets: [
        {
          label: `${label} (${unit})`,
          data: values,
          fill: false,
          borderColor: '#ffffff',
          backgroundColor: 'rgba(255,255,255,0.2)',
          tension: 0.3,
          pointRadius: 3,
          pointHoverRadius: 5,
          showLine: true
        }
      ]
    };
  }

  protected generateChartOptions(labels: string[]): ChartConfiguration['options'] {
    const total = labels.length;
    const visible = 25;

    return {
      responsive: true,
      maintainAspectRatio: true,
      aspectRatio: 2,
      plugins: {
        zoom: {
          pan: {
            enabled: true,
            mode: 'x'
          },
          zoom: {
            wheel: {enabled: false},
            pinch: {enabled: false},
            drag: {enabled: false}
          },
          limits: {
            x: {minRange: 10}
          }
        },
        legend: {
          labels: {
            color: '#fff',
            boxWidth: 0,
            padding: 10
          }
        },
        tooltip: {
          mode: 'nearest',
          intersect: false,
          displayColors: false
        }
      },
      scales: {
        x: {
          min: Math.max(0, total - visible),
          max: total,
          ticks: {display: false},
          grid: {color: '#ccc'}
        },
        y: {
          ticks: {color: '#fff'},
          grid: {color: '#ccc'}
        }
      }
    };
  }

  protected goToHomePage() {
    this.router.navigate(['/']);
  }

  protected onRangeChange(value: MeasurementResponseTypes) {
    this.selectedRange = value;

    if (this.selectedRange !== MeasurementResponseTypes.CUSTOM) {
      this.startDate = this.endDate = undefined;

      this.getMeasurements(this.selectedRange, this.weatherStationId);
    }
  }

  protected onDateChange() {
    if (this.startDate && this.endDate) {
      this.getMeasurements(this.selectedRange, this.weatherStationId, this.startDate, this.endDate);
    }
  }

  protected readonly MeasurementResponseTypes = MeasurementResponseTypes;
}
