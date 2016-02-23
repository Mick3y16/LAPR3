CREATE OR REPLACE PROCEDURE INSERTPROJECT (proj_name PROJECT.NAME%TYPE, 
                                          proj_description PROJECT.DESCRIPTION%TYPE)
IS
BEGIN
  INSERT INTO PROJECT(project_id, name, description) 
  VALUES(project_id.NEXTVAL, proj_name, proj_description); 
END insertproject;

/
  CREATE OR REPLACE PROCEDURE UPDATEPROJECT (oldProjName PROJECT.NAME%TYPE, 
										  newProjName PROJECT.NAME%TYPE, 
                                          newProjDescription PROJECT.DESCRIPTION%TYPE)
IS
  p_id project.project_id%TYPE;
BEGIN
  SELECT project_id into p_id FROM project where oldProjName=name;
  UPDATE project SET name=newProjName, description=newProjDescription WHERE p_id=PROJECT_ID;
END updateProject;

/
  CREATE OR REPLACE FUNCTION GETPROJECTLIST
return sys_refcursor
as curNameProjects sys_refcursor;
begin
 Open curnameProjects for SELECT name FROM Project ORDER BY PROJECT_ID ASC;
  return curNameProjects;

end getprojectlist;
/
  CREATE OR REPLACE FUNCTION GETDESCRIPTIONPROJECT(p_name in varchar2) 
 return varchar2 
as 
  v_proj_description    project.description%type;  
begin
  select description  into v_proj_description
  from project P where P.name = p_name;  
  return v_proj_description;
    
end getdescriptionproject;
/
  CREATE OR REPLACE FUNCTION GETJUNCTIONLIST
(
  p_name in varchar2 
) return sys_refcursor
as 
  curJunctions       sys_refcursor;
  v_project_id       project.project_id%type;

begin
  SELECT project_id into v_project_id
  FROM Project P
  WHERE P.name = p_name;
  
  Open curJunctions 
    for 
      SELECT *
      FROM Junction J
      WHERE J.project_id = v_project_id;
      
      return curJunctions;
      
end getjunctionlist;
/
  CREATE OR REPLACE FUNCTION GETSECTIONLIST
(
  name_project in varchar2
  
) return sys_refcursor
as 
  curSections       sys_refcursor;
  v_project_id      project.project_id%type;

begin
  SELECT project_id into v_project_id
  FROM Project P
  WHERE P.name = name_project;
  
  Open curSections 
    for 
      SELECT *
      FROM Section SC
      WHERE SC.project_id = v_project_id;
      
      return curSections;
end getsectionlist;
/
  CREATE OR REPLACE FUNCTION GETROADTYPOLOGY
(
  road_typology_n in number 
) return varchar
as
  v_typology ROAD_TYPOLOGY.TYPOLOGY%type;
begin
      SELECT typology into v_typology
      FROM road_typology R
      WHERE R.road_type_id = road_typology_n;
      return v_typology;
end getroadtypology;
/
  CREATE OR REPLACE FUNCTION GETSEGMENTLIST
(
  section_name in varchar2, 
  proj_name in varchar2
) return sys_refcursor
as 
  curSegments       sys_refcursor;
 begin
  
  Open curSegments 
    for 
      SELECT *
      FROM Segment S 
      WHERE S.section_id = (
            SELECT section_id 
            FROM Section SC
            WHERE SC.ROAD_NAME = section_name and 
            SC.PROJECT_ID = (
                  SELECT Project_id
                  FROM Project P
                  WHERE P.name = proj_name));
      
      return curSegments;
    
end getsegmentlist;
/
  CREATE OR REPLACE FUNCTION GETVEHICLELIST 
(
  name_proj in varchar2 
) return sys_refcursor
as 
  curVehicles       sys_refcursor;
  v_proj_id           project.project_id%type;
begin  
  SELECT project_id   into v_proj_id
  FROM Project P
  WHERE P.name = name_proj;
  Open curVehicles 
      for 
          SELECT *
          FROM VEHICLE V 
          WHERE V.PROJECT_ID = v_proj_id;
            
      return curVehicles; 
 
end getvehiclelist;
/
create or replace function getvehiclecombustion 
(
  name_proj PROJECT.NAME%type,
  name_vehicle VEHICLE.NAME%type 
) return sys_refcursor
as 
  curVehicleCombustion       sys_refcursor;
  v_proj_id           project.project_id%type;
begin
  SELECT project_id   into v_proj_id
  FROM Project P
  WHERE P.name = name_proj;
   Open curVehicleCombustion 
      for 
          SELECT *
          FROM VEHICLE_COMBUSTION VC
          WHERE VC.VEHICLE_ID = (  
                SELECT V.VEHICLE_ID
                FROM VEHICLE V
                WHERE V.name = NAME_VEHICLE AND V.PROJECT_ID=v_proj_id);
                
       return curVehicleCombustion;
end getvehiclecombustion;

/
create or replace function getvehicleelectric 
(
  name_proj PROJECT.NAME%type,
  name_vehicle VEHICLE.NAME%type 
) return sys_refcursor
as 
  curVehicleElectric       sys_refcursor;
  v_proj_id           project.project_id%type;
begin
  SELECT project_id   into v_proj_id
  FROM Project P
  WHERE P.name = name_proj;
   Open curVehicleElectric 
      for 
          SELECT *
          FROM VEHICLE_ELECTRIC VC
          WHERE VC.VEHICLE_ID = (  
                SELECT V.VEHICLE_ID
                FROM VEHICLE V
                WHERE V.name = NAME_VEHICLE AND V.PROJECT_ID=v_proj_id);
                
       return curVehicleElectric;
end getvehicleelectric;
/
  CREATE OR REPLACE FUNCTION GETGEARBOX
(
  proj_name PROJECT.NAME%type,
  name_vehicle VEHICLE.NAME%type 
) return sys_refcursor 

as 
  curgearbox       sys_refcursor;
begin
  Open curgearbox 
      for 
          SELECT *
          FROM  VEHICLE_GEAR_BOX VGB, VEHICLE V
          WHERE VGB.VEHICLE_ID = (  
                SELECT V.VEHICLE_ID
                FROM VEHICLE V
                WHERE V.name = NAME_VEHICLE AND V.PROJECT_ID=(SELECT P.PROJECT_ID
            FROM Project P
            WHERE proj_name = P.name)) ;
                
        return curgearbox;
end getgearbox;
/
  CREATE OR REPLACE FUNCTION GETTHROTTLELIST 
(
  proj_name in varchar2,
  veh_name in varchar2
) return sys_refcursor
as 
  curThrottle       sys_refcursor;
begin  
  Open curThrottle
      for 
          SELECT A.*
          FROM ACC_PEDAL_COMBUSTION A,VEHICLE V 
          WHERE V.PROJECT_ID = (
            SELECT P.PROJECT_ID
            FROM Project P
            WHERE proj_name = P.name) AND A.VEHICLE_ID = V.VEHICLE_ID AND V.NAME = veh_name;
            
      return curThrottle; 
 
end getthrottlelist;
/
  CREATE OR REPLACE FUNCTION GETREGIMELIST
(
  acc_id ACC_PEDAL_COMBUSTION.ACC_PEDAL_ID%TYPE
) return sys_refcursor
as 
  curRegime       sys_refcursor;
begin  
  Open curRegime
      for 
          SELECT R.*
          FROM ACC_PEDAL_COMBUSTION A,REGIME R
          WHERE A.ACC_PEDAL_ID = acc_id;
            
      return curRegime; 
 
end getregimelist;
/
  CREATE OR REPLACE PROCEDURE DELETEPROJECT(projName PROJECT.NAME%TYPE)
IS
BEGIN
  Delete project where name=projName;
END deleteProject;
/
  CREATE OR REPLACE PROCEDURE INSERTJUNCTION(project_name PROJECT.NAME%TYPE, 
                                          junction_name JUNCTION.NAME%TYPE)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
BEGIN
    SELECT project_id into id_project FROM project WHERE NAME=project_name;
    INSERT INTO JUNCTION(junction_id, project_id, name) 
    VALUES(junction_id.NEXTVAL, id_project, junction_name);
  
END insertJunction;
/
  CREATE OR REPLACE PROCEDURE INSERTSECTION(project_name PROJECT.NAME%TYPE, 
section_roadname SECTION.ROAD_NAME%TYPE, 
      section_typology ROAD_TYPOLOGY.TYPOLOGY%TYPE, section_toll SECTION.TOLL%TYPE, 
      section_wind_speed SECTION.WIND_SPEED%TYPE, section_wind_orientation SECTION.WIND_ORIENTATION%TYPE)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_typology ROAD_TYPOLOGY.ROAD_TYPE_ID%TYPE;
  
BEGIN
    
    SELECT project_id into id_project FROM project WHERE NAME=project_name;
    SELECT road_type_id into id_typology FROM ROAD_TYPOLOGY WHERE TYPOLOGY=section_typology;
    INSERT INTO SECTION(section_id, project_id, road_name, road_typology_id, toll, wind_speed, wind_orientation) 
      VALUES(section_id.NEXTVAL, id_project, section_roadname, 
      id_typology, section_toll, section_wind_speed, 
      section_wind_orientation);
  
END insertSection;
/
  CREATE OR REPLACE PROCEDURE INSERTSEGMENT(project_name PROJECT.NAME%TYPE, road_nameS SECTION.ROAD_NAME%TYPE, segment_s_index SEGMENT.S_INDEX%TYPE, 
        segment_initial_height SEGMENT.INITIAL_HEIGHT%TYPE, segment_angle SEGMENT.ANGLE%TYPE, 
        segment_s_length SEGMENT.S_LENGTH%TYPE, segment_max_velocity SEGMENT.MAXIMUM_VELOCITY%TYPE, 
        segment_min_velocity SEGMENT.MINIMUM_VELOCITY%TYPE, 
        segment_total_vehicles SEGMENT.MAXIMUM_NUMBER_VEHICLES%TYPE)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_section SECTION.SECTION_ID%TYPE;
BEGIN
  SELECT project_id into id_project FROM project WHERE NAME=project_name;
  SELECT section_id INTO id_section FROM section WHERE ROAD_NAME=road_nameS AND PROJECT_ID=id_project;
  INSERT INTO SEGMENT(segment_id, section_id, s_index, initial_height, angle, 
  s_length, maximum_velocity, minimum_velocity, maximum_number_vehicles) 
        VALUES(segment_id.NEXTVAL, id_section, segment_s_index, 
        segment_initial_height, segment_angle, segment_s_length, 
        segment_max_velocity, segment_min_velocity, segment_total_vehicles);
END insertSegment;
/
  CREATE OR REPLACE PROCEDURE INSERTVEHICLE(project_name PROJECT.NAME%TYPE, 
              v_name VEHICLE.NAME%type, v_desc VEHICLE.DESCRIPTION%type, 
              v_type VEHICLE.TYPE%type, v_fuel VEHICLE.FUEL%type, 
              v_mass VEHICLE.MASS%type, v_load VEHICLE.LOAD%type, 
              v_dc VEHICLE.DRAG_COEFFICIENT%type, v_fa VEHICLE.FRONTAL_AREA%type,
              v_rcc VEHICLE.RRC%type, v_wheel VEHICLE.WHEEL_SIZE%type,
              v_motorization VEHICLE.MOTORIZATION%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
BEGIN
  SELECT project_id into id_project FROM project WHERE NAME=project_name;
  INSERT INTO VEHICLE(vehicle_id, project_id, name, description, type, fuel, 
  mass, load, drag_coefficient, frontal_area, rrc, wheel_size, motorization)
  VALUES(vehicle_id.NEXTVAL, id_project, v_name, v_desc, v_type, v_fuel, v_mass,
  v_load, v_dc, v_fa, v_rcc, v_wheel, v_motorization);
END insertVehicle;
/
  CREATE OR REPLACE PROCEDURE INSERTVEHICLECOMBUSTION (project_name PROJECT.NAME%TYPE, 
              v_name VEHICLE.NAME%type, rmpmin VEHICLE_COMBUSTION.RPM_LOW%type,
              rmpmax VEHICLE_COMBUSTION.RPM_HIGH%type, fd VEHICLE_COMBUSTION.FINAL_DRIVE%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_vehicle vehicle.vehicle_id%type;
BEGIN
  SELECT project_id into id_project FROM project WHERE NAME=project_name;
  SELECT vehicle_id into id_vehicle FROM vehicle where name=v_name AND PROJECT_ID=id_project;
  
  
  INSERT INTO VEHICLE_COMBUSTION(vehicle_id, rpm_low, rpm_high, final_drive)
  VALUES(id_vehicle, rmpmin, rmpmax, fd);
END insertVehicleCombustion;
/
  CREATE OR REPLACE PROCEDURE INSERTACCPEDALCOMBUSTION (proj_name PROJECT.NAME%type, 
vehicle_name VEHICLE.NAME%type, percentage ACC_PEDAL_COMBUSTION.ACCEL_PEDAL_PERCENTAGE%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_vehicle VEHICLE.VEHICLE_ID%type;
BEGIN
    
    SELECT project_id into id_project FROM project WHERE NAME=proj_name;
    SELECT vehicle_id into id_vehicle FROM vehicle WHERE NAME=vehicle_name AND PROJECT_ID=id_project;
    INSERT INTO ACC_PEDAL_COMBUSTION(acc_pedal_id, vehicle_id, accel_pedal_percentage) 
      VALUES(acc_pedal_id.NEXTVAL, id_vehicle, percentage);
  
END insertACCpedalcombustion;
/
  CREATE OR REPLACE PROCEDURE INSERTREGIME (proj_name PROJECT.NAME%type, 
vehicle_name VEHICLE.NAME%type, percentage ACC_PEDAL_COMBUSTION.ACCEL_PEDAL_PERCENTAGE%type,
torque_r REGIME.TORQUE%type, rpm_low_r REGIME.RPM_LOW%type, rpm_high_r REGIME.RPM_HIGH%type, 
sfc_r REGIME.SFC%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_vehicle VEHICLE.VEHICLE_ID%type;
  acc_id ACC_PEDAL_COMBUSTION.ACC_PEDAL_ID%type;
BEGIN
    
    SELECT project_id into id_project FROM project WHERE NAME=proj_name;
    SELECT vehicle_id into id_vehicle FROM vehicle WHERE NAME=vehicle_name AND PROJECT_ID=id_project;
    SELECT acc_pedal_id into acc_id FROM ACC_PEDAL_COMBUSTION where ACCEL_PEDAL_PERCENTAGE=percentage AND VEHICLE_ID=id_vehicle;
    INSERT INTO REGIME(regime_id, torque, rpm_low, rpm_high, sfc, acc_pedal_id) VALUES (regime_id.nextVal, torque_r, rpm_low_r,
    rpm_high_r, sfc_r, acc_id);
END insertRegime;
/
  CREATE OR REPLACE PROCEDURE INSERTGEARBOX (proj_name PROJECT.NAME%type, 
vehicle_name VEHICLE.NAME%type, gearNumber VEHICLE_GEAR_BOX.GEAR_NUMBER%type,
ratio_g VEHICLE_GEAR_BOX.RATIO%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_vehicle VEHICLE.VEHICLE_ID%type;
BEGIN
    
    SELECT project_id into id_project FROM project WHERE NAME=proj_name;
    SELECT vehicle_id into id_vehicle FROM vehicle WHERE NAME=vehicle_name AND PROJECT_ID=id_project;
    INSERT INTO vehicle_gear_box(gear_id, vehicle_id, gear_number, ratio) VALUES
    (gear_id.NEXTVAL, id_vehicle, gearNumber, ratio_g);
    
END insertGearBox;
/
  CREATE OR REPLACE PROCEDURE INSERTVEHICLEELECTRIC (project_name PROJECT.NAME%TYPE, 
              v_name VEHICLE.NAME%type, rmpmin VEHICLE_ELECTRIC.RPM_LOW%type,
              rmpmax VEHICLE_ELECTRIC.RPM_HIGH%type, er VEHICLE_ELECTRIC.ENERGY_REGENERATION_RATIO%type, 
              fd VEHICLE_ELECTRIC.FINAL_DRIVE%type)
IS
  id_project PROJECT.PROJECT_ID%TYPE;
  id_vehicle vehicle.vehicle_id%type;
BEGIN
  SELECT project_id into id_project FROM project WHERE NAME=project_name;
  SELECT vehicle_id into id_vehicle FROM vehicle where name=v_name AND PROJECT_ID=id_project;
  
  
  INSERT INTO VEHICLE_ELECTRIC(vehicle_id, rpm_low, rpm_high, energy_regeneration_ratio, final_drive)
  VALUES(id_vehicle, rmpmin, rmpmax, er, fd);
END insertVehicleElectric;
/
create or replace PROCEDURE DELETESIMULATIONRUN(simulationRunName SIMULATION_RUN.NAME%TYPE)
IS
BEGIN
  Delete simulation_run where name=simulationRunName;
END DELETESIMULATIONRUN;
/
create or replace function get_acc_pedal
(
  name_proj PROJECT.NAME%type,
  name_vehicle VEHICLE.NAME%type 
) return sys_refcursor
as 
  curAccPedal      sys_refcursor;
  v_proj_id           project.project_id%type;
begin
  SELECT project_id   into v_proj_id
  FROM Project P
  WHERE P.name = name_proj;
   Open curAccPedal 
      for 
      SELECT *
          FROM ACC_PEDAL_COMBUSTION ACPC 
          WHERE ACPC.VEHICLE_ID = (  
                SELECT V.VEHICLE_ID
                FROM VEHICLE V
                WHERE V.name = NAME_VEHICLE AND V.PROJECT_ID=v_proj_id);
          
       return curAccPedal;
end get_acc_pedal;
/
create or replace function getregimelist
(
  acc_id ACC_PEDAL_COMBUSTION.ACC_PEDAL_ID%TYPE
) return sys_refcursor
as 
  curRegime       sys_refcursor;
begin  
  Open curRegime
      for 
          SELECT R.*
          FROM  REGIME R
          WHERE R.ACC_PEDAL_ID = acc_id;
            
      return curRegime; 
 
end getregimelist;
/
CREATE OR REPLACE PROCEDURE INSERTVEHICLEVELOCITY (
  PROJ_NAME PROJECT.NAME%TYPE,
  VEHICLE_NAME VEHICLE.NAME%TYPE,
  ROAD_NAME ROAD_TYPOLOGY.TYPOLOGY%TYPE,
  VELOCITY VEHICLE_VELOCITIES.VELOCITY%TYPE
)
IS 
 V_ROAD_TYPE_ID ROAD_TYPOLOGY.TYPOLOGY%TYPE;
 V_VEHICLE_ID VEHICLE.VEHICLE_ID%TYPE;
BEGIN

  SELECT ROAD_TYPE_ID INTO V_ROAD_TYPE_ID FROM ROAD_TYPOLOGY R WHERE R.TYPOLOGY = ROAD_NAME;
  SELECT VEHICLE_ID INTO V_VEHICLE_ID FROM VEHICLE V WHERE V.NAME = VEHICLE_NAME AND V.PROJECT_ID = (SELECT PROJECT_ID FROM PROJECT P WHERE P.NAME = PROJ_NAME);
  
  INSERT INTO VEHICLE_VELOCITIES(VEHICLE_VELOCITIES_ID, VEHICLE_ID, ROAD_TYPOLOGY_ID, VELOCITY)
  VALUES(VEHICLE_VELOCITIES_ID.NEXTVAL, V_VEHICLE_ID, V_ROAD_TYPE_ID, VELOCITY);
END INSERTVEHICLEVELOCITY;
/
CREATE OR REPLACE FUNCTION GETVEHICLEVELOCITIES 
(
  VEHICLE_NAME VEHICLE.NAME%TYPE,
  PROJ_NAME PROJECT.NAME%TYPE
) RETURN SYS_REFCURSOR
AS 
  CURVELOCITIES       SYS_REFCURSOR;
  V_PROJ_ID PROJECT.NAME%TYPE;
  V_VEHICLE_ID VEHICLE.VEHICLE_ID%TYPE;
 BEGIN
  SELECT PROJECT_ID INTO V_PROJ_ID FROM PROJECT P WHERE P.NAME = PROJ_NAME;
  SELECT VEHICLE_ID INTO V_VEHICLE_ID FROM VEHICLE V WHERE V.NAME = VEHICLE_NAME AND V.PROJECT_ID = V_PROJ_ID;
  
  OPEN CURVELOCITIES
    FOR 
      SELECT VELOCITY, TYPOLOGY
      FROM VEHICLE_VELOCITIES V, ROAD_TYPOLOGY R
      WHERE V.VEHICLE_ID = V_VEHICLE_ID AND V.ROAD_TYPOLOGY_ID = R.ROAD_TYPE_ID;
      
  RETURN CURVELOCITIES;
    
END GETVEHICLEVELOCITIES;