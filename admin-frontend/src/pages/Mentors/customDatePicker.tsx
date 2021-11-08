import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from "@material-ui/pickers";
import Grid from "@material-ui/core/Grid";

const CustomDatePicker = (props: any) => {
  const { startDate, setStartDate, finishDate, setFinishDate } = props;

  const handleDateChangeStart = (date: any) => {
    setStartDate(date);
    console.log(date);
  };
  const handleDateChangeFinish = (date: any) => {
    setFinishDate(date);
    console.log(date);
  };

  return (
    <div>
      <MuiPickersUtilsProvider utils={DateFnsUtils}>
        <Grid container justifyContent="center">
          <KeyboardDatePicker
            margin="normal"
            id="date-picker-dialog-start"
            label={<span style={{ opacity: 0.6 }}>Finish Date</span>}
            format="dd/MM/yyyy"
            clearable
            value={startDate}
            onChange={handleDateChangeStart}
            KeyboardButtonProps={{
              "aria-label": "change date",
            }}
          />
          <KeyboardDatePicker
            margin="normal"
            id="date-picker-dialog-finish"
            label={<span style={{ opacity: 0.6 }}>Finish Date</span>}
            format="dd/MM/yyyy"
            clearable
            value={finishDate}
            onChange={handleDateChangeFinish}
            KeyboardButtonProps={{
              "aria-label": "change date",
            }}
          />
        </Grid>
      </MuiPickersUtilsProvider>
    </div>
  );
};
export default CustomDatePicker;
